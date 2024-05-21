package com.siwar.API_pointeuse.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.API_pointeuse.entity.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepos extends JpaRepository<Team, Integer> {
	

	

}
