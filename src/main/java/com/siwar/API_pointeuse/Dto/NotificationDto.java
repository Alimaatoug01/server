package com.siwar.API_pointeuse.Dto;

import java.util.Date;

import com.siwar.API_pointeuse.entity.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
	private Integer id;
	private String type;
	private String message;
	private Date sendingDate;



}
