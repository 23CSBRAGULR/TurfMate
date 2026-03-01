package com.turfmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.turfmate.entity.Turf;
import com.turfmate.service.TurfService;

@RestController
@RequestMapping("/turfs")
@CrossOrigin
public class TurfController {

    @Autowired
    private TurfService turfService;

    @PostMapping
    public Turf addTurf(@RequestBody Turf turf) {
        return turfService.addTurf(turf);
    }

    @GetMapping
    public List<Turf> getAllTurfs() {
        return turfService.getAllTurfs();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurf(@PathVariable Long id) {
        turfService.deleteTurf(id);
        return ResponseEntity.ok("Turf Deleted");
    }
}