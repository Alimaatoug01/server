package com.siwar.API_pointeuse.Dto;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicHolidayDto {
	private Integer id;
	private String name;
	private Date startDate;
	private Date endDate;




}
