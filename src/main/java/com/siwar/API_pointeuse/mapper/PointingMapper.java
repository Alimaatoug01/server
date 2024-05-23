package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.PointingDto;
import com.siwar.API_pointeuse.entity.Pointing;


public class PointingMapper {

	public static PointingDto mapToPointingDto(Pointing pointing) {
		return  new PointingDto(
				pointing.getId(),
				pointing.getDateHour(),
				pointing.getService(),
				pointing.getEmployee(pointing.getEmployee())
		);
	}

	public static Pointing mapToPointing(PointingDto pointingDto) {
		return  new Pointing(
				pointingDto.getId(),
				pointingDto.getDateHour(),
				pointingDto.getService(),
				pointingDto.getEmployee()
		);
	}

}
