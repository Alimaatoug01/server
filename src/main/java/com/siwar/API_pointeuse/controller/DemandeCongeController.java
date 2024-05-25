package com.siwar.API_pointeuse.controller;

import com.siwar.API_pointeuse.entity.DemandeConge;
import com.siwar.API_pointeuse.service.DemandeCongeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/pointeuse/demandeConge")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class DemandeCongeController {
    @Autowired
    private DemandeCongeService demandeCongeService;

    @GetMapping
    public List<DemandeConge> getAllDemandeConges() {
        return demandeCongeService.getAllDemandeConges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeConge> getDemandeCongeById(@PathVariable Long id) {
        Optional<DemandeConge> demandeConge = demandeCongeService.getDemandeCongeById(id);
        return demandeConge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DemandeConge createDemandeConge(@RequestBody DemandeConge demandeConge) {
        return demandeCongeService.saveDemandeConge(demandeConge);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeConge> updateDemandeConge(@PathVariable Long id, @RequestBody DemandeConge demandeCongeDetails) {
        Optional<DemandeConge> optionalDemandeConge = demandeCongeService.getDemandeCongeById(id);
        if (optionalDemandeConge.isPresent()) {
            DemandeConge demandeConge = optionalDemandeConge.get();
            // demandeConge.setStartDate(demandeCongeDetails.getStartDate());
            // demandeConge.setEndDate(demandeCongeDetails.getEndDate());
            demandeConge.setConfirmed(demandeCongeDetails.isConfirmed());

            return ResponseEntity.ok(demandeCongeService.updateDemandeCongeStatus(id, demandeCongeDetails.isConfirmed()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemandeConge(@PathVariable Long id) {
        if (demandeCongeService.getDemandeCongeById(id).isPresent()) {
            demandeCongeService.deleteDemandeConge(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<DemandeConge> updateDemandeCongeStatus(@PathVariable Long id, @RequestParam boolean confirmed) {
        try {
            DemandeConge updatedDemandeConge = demandeCongeService.updateDemandeCongeStatus(id, confirmed);
            return ResponseEntity.ok(updatedDemandeConge);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}