package com.siwar.API_pointeuse.Dto;

import java.util.Date;

import com.siwar.API_pointeuse.entity.Assiduity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssiduityDto {
	
	private Integer id;
	private Date date;
	private Boolean presence;
	private Integer nbHourWorked;
	private Integer nbHourDue;
	private Boolean arriveLate;

	
}
