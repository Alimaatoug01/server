package com.siwar.API_pointeuse.Dto;


import java.time.LocalDateTime;
import java.util.Date;

import com.siwar.API_pointeuse.entity.Pointing;
import com.siwar.API_pointeuse.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointingDto {
	public PointingDto(Integer id2, LocalDateTime dateHour2, String service2, User employee) {
		// TODO Auto-generated constructor stub
	}
	private Integer id;
	private LocalDateTime dateHour;
	private String service;
	public Object getEmployee() {
		// TODO Auto-generated method stub
		return null;
	}





}
