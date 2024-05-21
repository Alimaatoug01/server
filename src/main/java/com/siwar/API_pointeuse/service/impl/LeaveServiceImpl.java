package com.siwar.API_pointeuse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.LeaveDto;
import com.siwar.API_pointeuse.entity.Leave;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.LeaveMapper;
import com.siwar.API_pointeuse.repos.LeaveRepos;
import com.siwar.API_pointeuse.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	private LeaveRepos leaverepos;

	@Override
	public LeaveDto add(LeaveDto leaveDto) {
		Leave leave=LeaveMapper.mapToLeave(leaveDto);
		Leave savedLeave = leaverepos.save(leave);
		return LeaveMapper.mapToLeaveDto(savedLeave) ;
	}

	@Override
	public LeaveDto getById(Integer id) {
		Leave leave = leaverepos.findById(id)
				.orElseThrow(()->
				       new ResourceNotFoundException("Leave is not exist with given id ;"+id));
				
				return LeaveMapper.mapToLeaveDto(leave);
	}


	@Override
	public void delete(Integer id) {
		 Leave leave=leaverepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Leave is not exist with given id:" + id));

		 leaverepos.deleteById(id);
	}

	@Override
	public LeaveDto update(Integer id, LeaveDto updatedLeave) {
       Leave leave=leaverepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Leave is not exist with given id:" + id));
	    
       leave.setStartDate(updatedLeave.getStartDate());
       leave.setEndDate(updatedLeave.getEndDate());
       leave.setType(updatedLeave.getType());
		
		Leave updatedLeaveObj= leaverepos.save(leave);
		
		return LeaveMapper.mapToLeaveDto(updatedLeaveObj);
	}

	@Override
	public List<LeaveDto> getAll() {
		List<Leave> leaves = leaverepos.findAll();
		return leaves.stream().map((leave)->LeaveMapper.mapToLeaveDto(leave))
				.collect(Collectors.toList());
	}

}
