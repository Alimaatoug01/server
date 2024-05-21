package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.LeaveDto;
import com.siwar.API_pointeuse.entity.Leave;

public class LeaveMapper {
	
	public static LeaveDto mapToLeaveDto(Leave leave) {
		return  new LeaveDto(
				leave.getId(),
				leave.getType(),
				leave.getStartDate(),
				leave.getEndDate()
		);
	}
	
	public static Leave mapToLeave(LeaveDto leaveDto) {
		return  new Leave(
				leaveDto.getId(),
				leaveDto.getType(),
				leaveDto.getStartDate(),
				leaveDto.getEndDate()
		);
	}


}
