package com.siwar.API_pointeuse.service;

import com.siwar.API_pointeuse.entity.DemandeConge;

import java.util.List;
import java.util.Optional;

public interface DemandeCongeService {
    List<DemandeConge> getAllDemandeConges();
    Optional<DemandeConge> getDemandeCongeById(Long id);
    DemandeConge saveDemandeConge(DemandeConge demandeConge);
    void deleteDemandeConge(Long id);
    DemandeConge updateDemandeCongeStatus(Long id, boolean confirmed);
}
