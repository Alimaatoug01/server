package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.WorkRegimeDto;


public interface WorkRegimeService {
	
	 WorkRegimeDto add( WorkRegimeDto  workRegimeDto);

	 WorkRegimeDto getById(Integer id);

	 void delete(Integer id);
	 
	 WorkRegimeDto update(WorkRegimeDto updatedWorkRegime, Integer id);
	 
	 List<WorkRegimeDto> getAll();
	 
	 

}
