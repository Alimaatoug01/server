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

import com.siwar.API_pointeuse.Dto.PublicHolidayDto;
import com.siwar.API_pointeuse.service.PublicHolidayService;


@RestController
@RequestMapping("/pointeuse/publicholiday")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PublicHolidayController {
	
	@Autowired
	private PublicHolidayService pbholidayService;
	
	//Build Add Public Holiday REST API
	@PostMapping("/add")
	public ResponseEntity<PublicHolidayDto> add(@RequestBody PublicHolidayDto pbholidayDto){
		PublicHolidayDto savedPublicHoliday = pbholidayService.add(pbholidayDto);
	    return new ResponseEntity<>(savedPublicHoliday, HttpStatus.CREATED);
	}
	
	//Build get Public Holiday REST API
	@GetMapping("/get{id}")
	public ResponseEntity<PublicHolidayDto> getById(Integer id){
		PublicHolidayDto pbholidayDto=pbholidayService.getById(id);
		return ResponseEntity.ok(pbholidayDto);
	}
	
	//Build get all Public Holiday REST API
	@GetMapping("/all")
	public ResponseEntity<List<PublicHolidayDto>> getAll(){
		List<PublicHolidayDto> pbholidayDto= pbholidayService.getAll();
	    return ResponseEntity.ok(pbholidayDto);
	}
	
	//Build update Public Holiday REST API
	@PutMapping("{id}")
	public ResponseEntity<PublicHolidayDto> updatedPublicHoliday(@PathVariable("id")Integer id ,
			                                             @RequestBody PublicHolidayDto updatedPublicHoliday){
 
		 PublicHolidayDto pbholidayDto=pbholidayService.update(id, updatedPublicHoliday);
	     return ResponseEntity.ok(pbholidayDto);
		 
	}
	
	//Build delete Public Holiday REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String>deletePublicHoliday(@PathVariable Integer id){
	
		pbholidayService.delete(id);
		return ResponseEntity.ok("Public holiday deleted successfully!");
	}


}
