package com.siwar.API_pointeuse.repos;






import com.siwar.API_pointeuse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.API_pointeuse.entity.Pointing;

import java.time.LocalDateTime;
import java.util.List;


public interface PointingRepos extends JpaRepository<Pointing, Integer> {
    boolean existsByEmployeeAndDateHourAndService(User employee, LocalDateTime dateHour, String service);
    List<Pointing> findByEmployeeId(Integer userId);
	

}
