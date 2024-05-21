package com.siwar.API_pointeuse.Dto;

import java.util.Date;

import com.siwar.API_pointeuse.entity.Leave;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {
	private Integer id;
	private String type;
	private Date startDate;
	private Date endDate;




}
