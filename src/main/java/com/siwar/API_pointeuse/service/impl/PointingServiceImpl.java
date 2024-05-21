package com.siwar.API_pointeuse.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.PointingDto;
import com.siwar.API_pointeuse.entity.Assiduity;
import com.siwar.API_pointeuse.entity.Pointing;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.AssiduityMapper;
import com.siwar.API_pointeuse.mapper.PointingMapper;
import com.siwar.API_pointeuse.repos.PointingRepos;
import com.siwar.API_pointeuse.service.PointingService;

@Service
public class PointingServiceImpl implements PointingService{
	
	@Autowired
	private PointingRepos pointingrepos;

	




	@Override
	public PointingDto update(PointingDto pointingDto, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PointingDto> getAll() {
		List<Pointing> pointings = pointingrepos.findAll();
		return pointings.stream().map((pointing)->PointingMapper.mapToPointingDto(pointing))
				.collect(Collectors.toList());
	}

}
