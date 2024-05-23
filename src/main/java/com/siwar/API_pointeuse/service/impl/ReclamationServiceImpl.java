package com.siwar.API_pointeuse.service.impl;

import com.siwar.API_pointeuse.entity.Reclamation;

import com.siwar.API_pointeuse.repos.ReclamationRepos;
import com.siwar.API_pointeuse.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private ReclamationRepos reclamationRepository;

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }

    @Override
    public Reclamation saveReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }
}
