package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.WorkRegimeDto;
import com.siwar.API_pointeuse.entity.WorkRegime;

public class WorkRegimeMapper {

	
	public static WorkRegimeDto mapToWorkRegimeDto(WorkRegime workRegime) {
		return  new WorkRegimeDto(
				workRegime.getId(),
				workRegime.getType(),
				workRegime.getStartTime(),
				workRegime.getEndTime(),
				workRegime.getDay(),
				workRegime.getStartBreak(),
				workRegime.getEndBreak(),
				workRegime.getNbHour()

		);
	}
	
	public static WorkRegime mapToWorkRegime(WorkRegimeDto workRegimeDto) {
		return  new WorkRegime(
				workRegimeDto.getId(),
				workRegimeDto.getType(),
				workRegimeDto.getStartTime(),
				workRegimeDto.getEndTime(),
				workRegimeDto.getDay(),
				workRegimeDto.getStartBreak(),
				workRegimeDto.getEndBreak(),
				workRegimeDto.getNbHour()
		);
	}
}
