package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.TeamDto;
import com.siwar.API_pointeuse.entity.Team;

public class TeamMapper {
	
	public static TeamDto mapToTeamDto(Team team) {
		return  new TeamDto(
				team.getId(),
				team.getName(),
				team.getUsers()
		);
	}
	
	public static Team mapToTeam(TeamDto teamDto) {
		return  new Team(
				teamDto.getId(),
				teamDto.getName(),
				teamDto.getUsers()
		);
	}

}
