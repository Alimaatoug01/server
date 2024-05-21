package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.LeaveDto;


public interface LeaveService {
	
	LeaveDto add( LeaveDto  leaveDto);

	 LeaveDto getById(Integer id);

	 void delete(Integer id);
	 
	 LeaveDto update(Integer id, LeaveDto updatedleave);
	 
	 List<LeaveDto> getAll();
	 
	 

}
