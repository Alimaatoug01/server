package com.siwar.API_pointeuse.Dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

import com.siwar.API_pointeuse.entity.Pointing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointingDto {
	private Integer id;
	private LocalDateTime dateHour;
	private String type;
	private File image;
	
	


}
