package com.siwar.API_pointeuse.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.AssiduityDto;
import com.siwar.API_pointeuse.repos.AssiduityRepos;
import com.siwar.API_pointeuse.service.AssiduityService;
import com.siwar.API_pointeuse.entity.Assiduity;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.AssiduityMapper;

@Service
public class AssiduityServiceImpl implements AssiduityService {
	
	@Autowired
	  private AssiduityRepos assrepos;
	  

	@Override
	public AssiduityDto add(AssiduityDto AssiduityDto) {
		 
		Assiduity assiduity=AssiduityMapper.mapToAssiduity(AssiduityDto);
		Assiduity savedAssiduity = assrepos.save(assiduity);
		return AssiduityMapper.mapToAssiduityDto(savedAssiduity) ;
	}

	@Override
	public AssiduityDto getById(Integer id) {
		Assiduity assiduity = assrepos.findById(id)
		.orElseThrow(()->
		       new ResourceNotFoundException("Assiduity is not exist with given id :"+id));
		
		return AssiduityMapper.mapToAssiduityDto(assiduity);
	}

	@Override
	public AssiduityDto getByDate(Date date) {
		Assiduity assiduity = assrepos.findByDate(date)
				.orElseThrow(()->
				       new ResourceNotFoundException("Assiduity is not exist with given date :"+date));
				
				return AssiduityMapper.mapToAssiduityDto(assiduity);
	}

	@Override
	public void delete(Integer id) {
		Assiduity assiduity=assrepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("assiduity is not exist with given id:" + id));

		assrepos.deleteById(id);
		
	}

	@Override
	public AssiduityDto update(Integer id , AssiduityDto updatedAssiduity) {
	
		Assiduity assiduity=assrepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("assiduity is not exist with given id:" + id));
	    
		assiduity.setDate(updatedAssiduity.getDate());
		assiduity.setPresence(updatedAssiduity.getPresence());
		assiduity.setNbHourWorked(updatedAssiduity.getNbHourWorked());
		assiduity.setNbHourDue(updatedAssiduity.getNbHourDue());
		assiduity.setArriveLate(updatedAssiduity.getArriveLate());
		
		Assiduity updatedAssiduityObj= assrepos.save(assiduity);
		
		return AssiduityMapper.mapToAssiduityDto(updatedAssiduityObj);
	}

	@Override
	public List<AssiduityDto> getAll() {
		List<Assiduity> assiduities = assrepos.findAll();
		return assiduities.stream().map((assiduity)->AssiduityMapper.mapToAssiduityDto(assiduity))
				.collect(Collectors.toList());
	}
}
	
	 