package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.PublicHolidayDto;


public interface PublicHolidayService {
	
	 PublicHolidayDto add( PublicHolidayDto  publicHolidayDto);

	 PublicHolidayDto getById(Integer id);

	 void delete(Integer id);
	 
	 PublicHolidayDto update(Integer id, PublicHolidayDto updatedPublicHoliday );
	 
	 List<PublicHolidayDto> getAll();
	 
	 

}
