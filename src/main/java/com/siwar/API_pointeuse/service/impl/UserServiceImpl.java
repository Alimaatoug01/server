package com.siwar.API_pointeuse.service.impl;

import com.siwar.API_pointeuse.Dto.UserDto;
import com.siwar.API_pointeuse.entity.ResetPasswordToken;
import com.siwar.API_pointeuse.entity.Role;
import com.siwar.API_pointeuse.entity.Team;
import com.siwar.API_pointeuse.entity.User;
import com.siwar.API_pointeuse.mapper.UserMapper;
import com.siwar.API_pointeuse.repos.ResetPasswordTokenRepos;
import com.siwar.API_pointeuse.repos.TeamRepos;
import com.siwar.API_pointeuse.repos.UserRepos;
import com.siwar.API_pointeuse.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {
    private final UserRepos userRepository;

    private final TeamRepos teamRepository;

    @Autowired
    private ResetPasswordTokenRepos resetPasswordTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepos userRepository, TeamRepos teamRepository) {

        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setDateBirth(userDto.getDateBirth());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserMapper.mapToUserDto(user);
        }
        return null; // Or throw an exception if the user is not found
    }



    @Override
    public UserDto getByDate(String date) {
        return null;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword()); // Ensure password is hashed
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getDateBirth() != null) {
            existingUser.setDateBirth(userDto.getDateBirth());
        }
        if (userDto.getRole() != null) {
            existingUser.setRole(userDto.getRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public UserDto affectTeamToUser(Integer userId, Integer teamId) {
        // Find the user by ID
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Find the team by ID
            Optional<Team> teamOptional = teamRepository.findById(teamId);
            if (teamOptional.isPresent()) {
                Team team = teamOptional.get();

                // Set the team for the user
                user.setTeam(team);

                // Save the updated user
                User updatedUser = userRepository.save(user);

                // Return the updated user DTO
                return UserMapper.mapToUserDto(updatedUser);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Override
    public UserDto removeUserFromTeam(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Remove the team reference from the user
            user.setTeam(null);

            // Save the updated user
            User updatedUser = userRepository.save(user);

            // Return the updated user DTO
            return UserMapper.mapToUserDto(updatedUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);


    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public ResetPasswordToken createResetPasswordToken(User user) {
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken(user);
        return resetPasswordTokenRepository.save(resetPasswordToken);
    }
    @Override
    public ResetPasswordToken findByResetPasswordToken(String token) {
        return resetPasswordTokenRepository.findByToken(token);
    }

    @Override
    public void resetPassword(String email, String password) {
        User user = findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public int getCountByRole(Role role) {
        return userRepository.countByRole(role);
    }


}
