package com.siwar.API_pointeuse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.WorkRegimeDto;
import com.siwar.API_pointeuse.entity.WorkRegime;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.WorkRegimeMapper;
import com.siwar.API_pointeuse.repos.WorkRegimeRepos;
import com.siwar.API_pointeuse.service.WorkRegimeService;
@Service
public class WorkRegimeServiceImpl implements WorkRegimeService{
	
	@Autowired
	  private WorkRegimeRepos workregimerepos;

	@Override
	public WorkRegimeDto add(WorkRegimeDto workRegimeDto) {
		WorkRegime workregime=WorkRegimeMapper.mapToWorkRegime(workRegimeDto);
		WorkRegime savedworkregime = workregimerepos.save(workregime);
		return WorkRegimeMapper.mapToWorkRegimeDto(savedworkregime) ;
	}

	@Override
	public WorkRegimeDto getById(Integer id) {
		WorkRegime workregime = workregimerepos.findById(id)
				.orElseThrow(()->
				       new ResourceNotFoundException("Work Regime is not exist with given id :"+id));
				
				return WorkRegimeMapper.mapToWorkRegimeDto(workregime);
	}

	@Override
	public void delete(Integer id) {
		WorkRegime workregime=workregimerepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Work Regime is not exist with given id:" + id));

		workregimerepos.deleteById(id);
	}

	@Override
	public WorkRegimeDto update(WorkRegimeDto updatedWorkRegime, Integer id) {
		WorkRegime workregime=workregimerepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Work Regime is not exist with given id:" + id));
	    
		workregime.setStartTime(updatedWorkRegime.getStartTime());
		workregime.setEndTime(updatedWorkRegime.getEndTime());
		workregime.setDay(updatedWorkRegime.getDay());
		workregime.setStartBreak(updatedWorkRegime.getStartBreak());
		workregime.setEndBreak(updatedWorkRegime.getEndBreak());
		workregime.setNbHour(updatedWorkRegime.getNbHour());
		workregime.setType(updatedWorkRegime.getType());
		
		
		WorkRegime updatedWorkRegimeObj= workregimerepos.save(workregime);
		
		return WorkRegimeMapper.mapToWorkRegimeDto(updatedWorkRegimeObj);
	}

	@Override
	public List<WorkRegimeDto> getAll() {
		List<WorkRegime> workregimes = workregimerepos.findAll();
		return workregimes.stream().map((workregime)->WorkRegimeMapper.mapToWorkRegimeDto(workregime))
				.collect(Collectors.toList());
	}

}
