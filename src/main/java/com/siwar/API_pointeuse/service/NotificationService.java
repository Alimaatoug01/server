package com.siwar.API_pointeuse.service;

import java.util.List;

import com.siwar.API_pointeuse.Dto.NotificationDto;



public interface NotificationService {
	
	/* NotificationDto add( NotificationDto  notificationDto);

	 NotificationDto getById(Integer id);

	 NotificationDto getByDate(String date);

	 void delete(Integer id);
	 
	 NotificationDto update(NotificationDto notificationDto, Integer id);
	 
	 List<NotificationDto> getAll();*/

    void sendResetPasswordEmail(String emailAddress, String token);

}
