package com.siwar.API_pointeuse.service;

import com.siwar.API_pointeuse.entity.Reclamation;

import java.util.List;
import java.util.Optional;

public interface ReclamationService {
    List<Reclamation> getAllReclamations();
    Optional<Reclamation> getReclamationById(Long id);
    Reclamation saveReclamation(Reclamation reclamation);
    void deleteReclamation(Long id);
}
