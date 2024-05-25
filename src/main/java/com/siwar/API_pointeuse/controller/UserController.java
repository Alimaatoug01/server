package com.siwar.API_pointeuse.controller;

import com.siwar.API_pointeuse.Dto.UserDto;
import com.siwar.API_pointeuse.entity.ResetPasswordToken;
import com.siwar.API_pointeuse.entity.Role;
import com.siwar.API_pointeuse.entity.User;
import com.siwar.API_pointeuse.payload.request.ForgotPasswordEmailRequest;
import com.siwar.API_pointeuse.payload.request.LoginRequest;
import com.siwar.API_pointeuse.payload.response.ApiResponse;
import com.siwar.API_pointeuse.payload.response.JwtResponse;
import com.siwar.API_pointeuse.security.jwt.JwtUtils;
import com.siwar.API_pointeuse.security.services.UserDetailsImpl;
import com.siwar.API_pointeuse.service.NotificationService;
import com.siwar.API_pointeuse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("Received login request: " + userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/addadmin")
    public UserDto admin(@RequestBody UserDto userDto) {
        userDto.setRole(Role.admin);

        return userService.add(userDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        User foundUser = userService.findByEmail(userDto.getEmail());
        if (foundUser != null) {
            if (foundUser.getPassword().equals(userDto.getPassword())) {
                Map<String, Object> response = new HashMap<>();
                response.put("userId", foundUser.getId());
                response.put("role", foundUser.getRole());
                return ResponseEntity.ok(response);
            } else {
                // Mot de passe incorrect
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } else {
            // Utilisateur non trouvé
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @PutMapping("/{userId}/affect-team/{teamId}")
    public ResponseEntity<UserDto> affectTeamToUser(@PathVariable Integer userId, @PathVariable Integer teamId) {
        UserDto updatedUser = userService.affectTeamToUser(userId, teamId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        // Encode the password before saving the user
        String encodedPassword = encoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        UserDto addedUser = userService.add(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    }



    @GetMapping("/by-role/rh")
    public int getRhUsers() {
        return userService.getCountByRole(Role.rh);
    }

    @GetMapping("/by-role/superviseur")
    public int getSuperviseurUsers() {
        return userService.getCountByRole(Role.superviseur);
    }

    @GetMapping("/by-role/employee")
    public int getEmployeeUsers() {
        return userService.getCountByRole(Role.employee);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        return userService.update(userDto, id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }
    @PutMapping("/{userId}/remove-team")
    public ResponseEntity<UserDto> removeUserFromTeam(@PathVariable Integer userId) {
        UserDto updatedUser = userService.removeUserFromTeam(userId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/sendForgotPasswordEmail", produces = "application/json")
    public ResponseEntity<?> sendForgotPasswordEmail(@Valid @RequestBody ForgotPasswordEmailRequest request) {
        if (userService.existsByEmail(request.getForgotPasswordEmail())) {
            User user = userService.findByEmail(request.getForgotPasswordEmail());
            ResetPasswordToken resetPasswordToken = userService.createResetPasswordToken(user);
            notificationService.sendResetPasswordEmail(request.getForgotPasswordEmail(), resetPasswordToken.getToken());
            return ResponseEntity.ok(new ApiResponse("Please check your email for a password reset link", true));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Email not found", false));
        }
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ForgotPasswordEmailRequest request) {
        ResetPasswordToken resetPasswordToken = userService.findByResetPasswordToken(request.getToken());
        if (resetPasswordToken == null) throw new RuntimeException("Invalid token");

        Calendar calendar = Calendar.getInstance();
        if ((resetPasswordToken.getExpiredAt().getTime() - calendar.getTime().getTime()) <= 0) {
            throw new RuntimeException("Link expired. Generate new link from http://localhost:3000/signup");
        }

        userService.resetPassword(request.getForgotPasswordEmail(), request.getNewPassword());
        return ResponseEntity.ok(new ApiResponse("Password reset successfully", true));
    }


}
