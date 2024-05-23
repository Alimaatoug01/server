package com.siwar.API_pointeuse.controller;

import java.util.List;

import com.siwar.API_pointeuse.entity.Pointing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siwar.API_pointeuse.Dto.PointingDto;
import com.siwar.API_pointeuse.service.PointingService;

@RestController
@RequestMapping("/api/pointings")
public class PointingController {

    @Autowired
    private PointingService pointingService;

    @PostMapping("/add")
    public ResponseEntity<PointingDto> add() {
        PointingDto savedPointing = pointingService.add();
        return new ResponseEntity<>(savedPointing, HttpStatus.CREATED);
    }

    @GetMapping("/csvdata")
    public ResponseEntity<List<String[]>> getAllCsvData() {
        List<String[]> csvData = pointingService.getAllCsvData();
        return ResponseEntity.ok(csvData);
    }

    @GetMapping
    public ResponseEntity<List<Pointing>> getAllPointings() {
        List<Pointing> pointings = pointingService.getAllPointings();
        return ResponseEntity.ok(pointings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pointing>> getPointingsByUserId(@PathVariable Integer userId) {
        List<Pointing> pointings = pointingService.getPointingsByUserId(userId);
        return ResponseEntity.ok(pointings);
    }
}
