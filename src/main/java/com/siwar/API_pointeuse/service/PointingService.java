package com.siwar.API_pointeuse.service;
import java.util.List;

import com.siwar.API_pointeuse.Dto.PointingDto;
import com.siwar.API_pointeuse.entity.Pointing;


public interface PointingService {

	PointingDto add();
	PointingDto update(PointingDto pointingDto, Integer id);

	List<String[]> getAllCsvData();

	List<Pointing> getAllPointings();

	List<Pointing> getPointingsByUserId(Integer userId);
	void processCsvData();



}


