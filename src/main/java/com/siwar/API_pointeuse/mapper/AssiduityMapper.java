package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.AssiduityDto;
import com.siwar.API_pointeuse.entity.Assiduity;

public class AssiduityMapper {
	
	public static AssiduityDto mapToAssiduityDto(Assiduity ass) {
		return  new AssiduityDto(
				ass.getId(),
				ass.getDate(),
				ass.getPresence(),
				ass.getNbHourWorked(),
				ass.getNbHourDue(),
				ass.getArriveLate()
		);
	}
	
	public static Assiduity mapToAssiduity(AssiduityDto assDto) {
		return  new Assiduity(
				assDto.getId(),
				assDto.getDate(),
				assDto.getPresence(),
				assDto.getNbHourWorked(),
				assDto.getNbHourDue(),
				assDto.getArriveLate()
		);
	}

}
