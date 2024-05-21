package com.siwar.API_pointeuse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siwar.API_pointeuse.Dto.TeamDto;
import com.siwar.API_pointeuse.service.TeamService;


@RestController
@RequestMapping("/pointeuse/team")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	//Build Add team REST API
	@PostMapping("/add")
	public ResponseEntity<TeamDto> add(@RequestBody TeamDto teamDto){
		TeamDto savedTeam = teamService.add(teamDto);
	    return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
	}
	
	//Build get team REST API
	@GetMapping("/get/{id}")
	public ResponseEntity<TeamDto> getById(@PathVariable Integer id){
		TeamDto teamDto=teamService.getById(id);
		return ResponseEntity.ok(teamDto);
	}
	
	//Build get all team REST API
	@GetMapping("/all")
	public ResponseEntity<List<TeamDto>> getAll(){
		List<TeamDto> teamDto= teamService.getAll();
	    return ResponseEntity.ok(teamDto);
	}
	
	//Build update Team REST API
	@PutMapping("{id}")
	public ResponseEntity<TeamDto> updatedTeam(@PathVariable("id")Integer id ,
			                                             @RequestBody TeamDto updatedTeam){
 
		TeamDto teamDto=teamService.update(id, updatedTeam);
	     return ResponseEntity.ok(teamDto);
		 
	}
	
	//Build delete team REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteTeam(@PathVariable Integer id){
	
		teamService.delete(id);
		return ResponseEntity.ok("Team deleted successfully!");
	}

}
