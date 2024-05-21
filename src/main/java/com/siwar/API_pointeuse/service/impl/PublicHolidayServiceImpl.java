package com.siwar.API_pointeuse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.PublicHolidayDto;
import com.siwar.API_pointeuse.entity.PublicHoliday;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.PublicHolidayMapper;
import com.siwar.API_pointeuse.repos.PublicHolidayRepos;
import com.siwar.API_pointeuse.service.PublicHolidayService;
@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
	
	@Autowired
	  private PublicHolidayRepos pbholidayrepos;

	@Override
	public PublicHolidayDto add(PublicHolidayDto publicHolidayDto) {
		PublicHoliday pbholiday=PublicHolidayMapper.mapToPublicHoliday(publicHolidayDto);
		PublicHoliday savedpbholiday = pbholidayrepos.save(pbholiday);
		return PublicHolidayMapper.mapToPublicHolidayDto(savedpbholiday) ;
	}

	@Override
	public PublicHolidayDto getById(Integer id) {
		PublicHoliday pbholiday = pbholidayrepos.findById(id)
				.orElseThrow(()->
				       new ResourceNotFoundException("Public Holiday is not exist with given id :"+id));
				
				return PublicHolidayMapper.mapToPublicHolidayDto(pbholiday);
	}


	@Override
	public void delete(Integer id) {
		PublicHoliday pbholiday=pbholidayrepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Public Holiday is not exist with given id:" + id));

		pbholidayrepos.deleteById(id);
	}

	@Override
	public PublicHolidayDto update(Integer id, PublicHolidayDto updatedPublicHoliday ) {
		PublicHoliday pbholiday=pbholidayrepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Public Holiday is not exist with given id:" + id));
	    
		pbholiday.setStartDate(updatedPublicHoliday.getStartDate());
		pbholiday.setEndDate(updatedPublicHoliday.getEndDate());
		pbholiday.setName(updatedPublicHoliday.getName());
		
		PublicHoliday updatedPublicHolidayObj= pbholidayrepos.save(pbholiday);
		
		return PublicHolidayMapper.mapToPublicHolidayDto(updatedPublicHolidayObj);
	}

	@Override
	public List<PublicHolidayDto> getAll() {
		List<PublicHoliday> pbholidays = pbholidayrepos.findAll();
		return pbholidays.stream().map((pbholiday)->PublicHolidayMapper.mapToPublicHolidayDto(pbholiday))
				.collect(Collectors.toList());
	}

}
