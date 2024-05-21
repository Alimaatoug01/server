package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.UserDto;
import com.siwar.API_pointeuse.entity.User;

public class UserMapper {
	public static UserDto mapToUserDto(User user) {
		return new UserDto(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getDateBirth(),
				user.getEmail(),
				user.getPhoneNumber(),
				user.getRole()
		);
	}

	public static User mapToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setDateBirth(userDto.getDateBirth());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setRole(userDto.getRole());
		return user;
	}

}
