package com.siwar.API_pointeuse.repos;

import com.siwar.API_pointeuse.entity.Assiduity;
import com.siwar.API_pointeuse.entity.DemandeConge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeCongeRepos extends JpaRepository<DemandeConge, Long> {
}
