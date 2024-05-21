package com.siwar.API_pointeuse.controller;

import java.util.Date;
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

import com.siwar.API_pointeuse.Dto.AssiduityDto;
import com.siwar.API_pointeuse.service.AssiduityService;

@RestController
@RequestMapping("/pointeuse/assiduity")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class AssiduityController {
	
	@Autowired
	private AssiduityService assService;
	
	//Build Add Assiduity REST API
	@PostMapping("/add")
	public ResponseEntity<AssiduityDto> add(@RequestBody AssiduityDto assDto){
		AssiduityDto savedAssiduity = assService.add(assDto);
	    return new ResponseEntity<>(savedAssiduity, HttpStatus.CREATED);
	}
	
	//Build get Assiduity REST API
	@GetMapping("/get{id}")
	public ResponseEntity<AssiduityDto> getById(Integer id){
		AssiduityDto assiduityDto=assService.getById(id);
		return ResponseEntity.ok(assiduityDto);
	}
	
	//Build get Assiduity REST API
		@GetMapping("/get{date}")
		public ResponseEntity<AssiduityDto> getByDate(Date date){
			AssiduityDto assiduityDto=assService.getByDate(date);
			return ResponseEntity.ok(assiduityDto);
		}
	
	//Build get all Assiduity REST API
	@GetMapping("/all")
	public ResponseEntity<List<AssiduityDto>> getAll(){
		List<AssiduityDto> assiduityDto= assService.getAll();
	    return ResponseEntity.ok(assiduityDto);
	}
	
	//Build update Assiduity REST API
	@PutMapping("{id}")
	public ResponseEntity<AssiduityDto> updatedAssiduity(@PathVariable("id")Integer id ,
			                                             @RequestBody AssiduityDto updatedAssiduity){
 
		 AssiduityDto assiduityDto=assService.update(id, updatedAssiduity);
	     return ResponseEntity.ok(assiduityDto);
		 
	}
	
	//Build delete Assiduity REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteAssiduity(@PathVariable Integer id){
	
		assService.delete(id);
		return ResponseEntity.ok("assiduity deleted successfully!");
	}

}
