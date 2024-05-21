package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.PublicHolidayDto;
import com.siwar.API_pointeuse.entity.PublicHoliday;

public class PublicHolidayMapper {
	
	
	public static PublicHolidayDto mapToPublicHolidayDto(PublicHoliday pubHoliday) {
		return  new PublicHolidayDto(
				pubHoliday.getId(),
				pubHoliday.getName(),
				pubHoliday.getStartDate(),
				pubHoliday.getEndDate()
		);
	}
	
	public static PublicHoliday mapToPublicHoliday(PublicHolidayDto pubHolidayDto) {
		return  new PublicHoliday(
				pubHolidayDto.getId(),
				pubHolidayDto.getName(),
				pubHolidayDto.getStartDate(),
				pubHolidayDto.getEndDate()
		);
	}

}
