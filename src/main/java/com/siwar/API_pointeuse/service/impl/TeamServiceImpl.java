package com.siwar.API_pointeuse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.siwar.API_pointeuse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.TeamDto;
import com.siwar.API_pointeuse.entity.Team;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.TeamMapper;
import com.siwar.API_pointeuse.repos.TeamRepos;
import com.siwar.API_pointeuse.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	  private TeamRepos teamrepos;

	@Override
	public TeamDto add(TeamDto teamDto) {
		if (teamDto.getName() == null) {
			throw new IllegalArgumentException("Team name cannot be null");
		}

		Team team = TeamMapper.mapToTeam(teamDto);

		// Log the team object to the console
		System.out.println("Team object to be saved: " + team);

		Team savedTeam = teamrepos.save(team);
		return TeamMapper.mapToTeamDto(savedTeam);
	}

	@Override
	public TeamDto getById(Integer id) {
		Team team = teamrepos.findById(id)
				.orElseThrow(()->
				       new ResourceNotFoundException("Team is not exist with given id :"+id));
				
				return TeamMapper.mapToTeamDto(team);
	}

	@Override
	public void delete(Integer id) {
		Team team = teamrepos.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team is not exist with given id:" + id));

		// Remove team reference from all associated users
		for (User user : team.getUsers()) {
			user.setTeam(null);
		}

		teamrepos.deleteById(id);
	}


	@Override
	public TeamDto update( Integer id, TeamDto updatedTeam) {
		Team team=teamrepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teamis not exist with given id:" + id));
	 
		team.setName(updatedTeam.getName());
		
		Team updatedTeamObj= teamrepos.save(team);
		
		return TeamMapper.mapToTeamDto(updatedTeamObj);
	}

	@Override
	public List<TeamDto> getAll() {
		List<Team> teams = teamrepos.findAll();
		return teams.stream().map((team)->TeamMapper.mapToTeamDto(team))
				.collect(Collectors.toList());
	}

}
