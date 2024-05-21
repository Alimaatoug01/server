package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.UserDto;
import com.siwar.API_pointeuse.entity.Role;
import com.siwar.API_pointeuse.entity.User;

public interface UserService {
	 UserDto add( UserDto  userDto);

	 UserDto getById(Integer id);

	 UserDto getByDate(String date);

	 void delete(Integer id);
	 
	 UserDto update(UserDto userDto, Integer id);
	 
	 List<User> getAll();
	UserDto removeUserFromTeam(Integer userId);
	 UserDto affectTeamToUser(Integer userId, Integer teamId);
	User findByEmail(String email);

	List<UserDto> getByRole(Role role);

	int getCountByRole(Role rh);

}
