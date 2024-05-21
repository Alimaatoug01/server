package com.siwar.API_pointeuse.Dto;

import java.sql.Time;

import com.siwar.API_pointeuse.entity.WorkRegime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkRegimeDto {
	private Integer id;
	private String type;
	private Time startTime;
	private Time endTime;
	private String day;
	private Time startBreak;
	private Time endBreak;
	private Integer nbHour; 

	


}
