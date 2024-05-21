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

import com.siwar.API_pointeuse.Dto.LeaveDto;
import com.siwar.API_pointeuse.service.LeaveService;

@RestController
@RequestMapping("/pointeuse/leave")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	
	//Build Add Leave REST API
	@PostMapping("/add")
	public ResponseEntity<LeaveDto> add(@RequestBody LeaveDto leaveDto){
		LeaveDto savedLeave = leaveService.add(leaveDto);
	    return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);
	}
	
	//Build get Leave REST API
	@GetMapping("/get{id}")
	public ResponseEntity<LeaveDto> getById(Integer id){
		LeaveDto leaveDto=leaveService.getById(id);
		return ResponseEntity.ok(leaveDto);
	}
	
	//Build get all Leave REST API
	@GetMapping("/all")
	public ResponseEntity<List<LeaveDto>> getAll(){
		List<LeaveDto> leaveDto= leaveService.getAll();
	    return ResponseEntity.ok(leaveDto);
	}
	
	//Build update Leave REST API
	@PutMapping("{id}")
	public ResponseEntity<LeaveDto> updatedLeave(@PathVariable("id")Integer id ,
			                                             @RequestBody LeaveDto updatedLeave){
 
		LeaveDto leaveDto=leaveService.update(id, updatedLeave);
	     return ResponseEntity.ok(leaveDto);
		 
	}
	
	//Build delete Public Holiday REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteLeave(@PathVariable Integer id){
	
		leaveService.delete(id);
		return ResponseEntity.ok("Leave deleted successfully!");
	}

}
