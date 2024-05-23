package com.siwar.API_pointeuse.controller;

import com.siwar.API_pointeuse.entity.Reclamation;
import com.siwar.API_pointeuse.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pointeuse/reclamation")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @GetMapping
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable Long id) {
        Optional<Reclamation> reclamation = reclamationService.getReclamationById(id);
        return reclamation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reclamation createReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.saveReclamation(reclamation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable Long id, @RequestBody Reclamation reclamationDetails) {
        Optional<Reclamation> optionalReclamation = reclamationService.getReclamationById(id);
        if (optionalReclamation.isPresent()) {
            Reclamation reclamation = optionalReclamation.get();
            reclamation.setContent(reclamationDetails.getContent());
            reclamation.setUser(reclamationDetails.getUser());
            return ResponseEntity.ok(reclamationService.saveReclamation(reclamation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        if (reclamationService.getReclamationById(id).isPresent()) {
            reclamationService.deleteReclamation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
