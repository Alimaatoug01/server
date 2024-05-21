package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.PointingDto;
import com.siwar.API_pointeuse.entity.Pointing;

public class PointingMapper {
	
	public static PointingDto mapToPointingDto(Pointing pointing) {
		return  new PointingDto(
				pointing.getId(),
				pointing.getDateHour(),
				pointing.getType(),
				pointing.getImage()
		);
	}
	
	public static Pointing mapToPointing(PointingDto pointingDto) {
		return  new Pointing(
				pointingDto.getId(),
				pointingDto.getDateHour(),
				pointingDto.getType(),
				pointingDto.getImage()
		);
	}

}
