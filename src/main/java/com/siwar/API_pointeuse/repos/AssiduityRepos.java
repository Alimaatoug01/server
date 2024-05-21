package com.siwar.API_pointeuse.repos;


import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.API_pointeuse.entity.Assiduity;

public interface AssiduityRepos extends JpaRepository<Assiduity, Integer> {

	Optional<Assiduity> findByDate(Date date);



}
