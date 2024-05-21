package com.siwar.API_pointeuse.mapper;

import com.siwar.API_pointeuse.Dto.NotificationDto;
import com.siwar.API_pointeuse.entity.Notification;

public class NotificationMapper {
	
	public static NotificationDto mapToNotificationDto(Notification notif) {
		return  new NotificationDto(
				notif.getId(),
				notif.getType(),
				notif.getMessage(),
				notif.getSendingDate()
		);
	}
	
	public static Notification mapToNotification(NotificationDto notifDto) {
		return  new Notification(
				notifDto.getId(),
				notifDto.getType(),
				notifDto.getMessage(),
				notifDto.getSendingDate()
		);
	}

}
