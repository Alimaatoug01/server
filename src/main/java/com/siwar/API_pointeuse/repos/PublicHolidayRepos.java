package com.siwar.API_pointeuse.repos;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.API_pointeuse.entity.PublicHoliday;

public interface PublicHolidayRepos extends JpaRepository<PublicHoliday, Integer> {

	

}
