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

import com.siwar.API_pointeuse.Dto.WorkRegimeDto;
import com.siwar.API_pointeuse.service.WorkRegimeService;


@RestController
@RequestMapping("/pointeuse/workregime")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class WorkRegimeController {
	
	@Autowired
	private WorkRegimeService workregimeService;
	
	//Build Add work regime REST API
	@PostMapping("/add")
	public ResponseEntity<WorkRegimeDto> add(@RequestBody WorkRegimeDto workregimeDto){
		WorkRegimeDto savedWorkRegime = workregimeService.add(workregimeDto);
	    return new ResponseEntity<>(savedWorkRegime, HttpStatus.CREATED);
	}
	
	//Build get Work Regime REST API
	@GetMapping("/get/{id}")
	public ResponseEntity<WorkRegimeDto> getById(@PathVariable("id")Integer id ){
		WorkRegimeDto workregimeDto=workregimeService.getById(id);
		return ResponseEntity.ok(workregimeDto);
	}
	
	//Build get all Work Regime REST API
	@GetMapping("/all")
	public ResponseEntity<List<WorkRegimeDto>> getAll(){
		List<WorkRegimeDto> workregimeDto= workregimeService.getAll();
	    return ResponseEntity.ok(workregimeDto);
	}
	
	//Build update Work Regime REST API
	@PutMapping("{id}")
	public ResponseEntity<WorkRegimeDto> updatedWorkRegime(@PathVariable("id")Integer id ,
			                                             @RequestBody WorkRegimeDto updatedWorkRegime){
 
		WorkRegimeDto workregimeDto=workregimeService.update(updatedWorkRegime, id);
	     return ResponseEntity.ok(workregimeDto);
		 
	}
	
	//Build delete tWork Regime REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteWorkRegime(@PathVariable Integer id){
	
		workregimeService.delete(id);
		return ResponseEntity.ok("Work Regime deleted successfully!");
	}

}
