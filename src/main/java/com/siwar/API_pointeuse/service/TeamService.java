package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.TeamDto;


public interface TeamService {
	
	 TeamDto add( TeamDto  teamDto);

	 TeamDto getById(Integer id);

	 void delete(Integer id);
	 
	 TeamDto update( Integer id, TeamDto updatedTeam);
	 
	 List<TeamDto> getAll();
	 
	 

}
