package com.siwar.API_pointeuse.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.API_pointeuse.entity.Notification;

public interface NotificationRepos extends JpaRepository<Notification, Integer> {

}
