package com.siwar.API_pointeuse.service;

import java.util.Date;
import java.util.List;

import com.siwar.API_pointeuse.Dto.AssiduityDto;

public interface AssiduityService {
	
	 AssiduityDto add( AssiduityDto  AssiduityDto);

	 AssiduityDto getById(Integer id);

	 AssiduityDto getByDate(Date date);

	 void delete(Integer id);
	 
	 AssiduityDto update(Integer id , AssiduityDto updatedAssiduity );
	 
	 List<AssiduityDto> getAll();
	 
	 
}
