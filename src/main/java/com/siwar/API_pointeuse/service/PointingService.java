package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.PointingDto;


public interface PointingService {
	
	 
	 PointingDto update(PointingDto pointingDto, Integer id);
	 
	 List<PointingDto> getAll();
	 
	 

}
