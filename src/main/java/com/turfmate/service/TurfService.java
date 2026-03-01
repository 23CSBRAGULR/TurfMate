package com.turfmate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turfmate.entity.Turf;
import com.turfmate.repository.TurfRepository;

@Service
public class TurfService {

    @Autowired
    private TurfRepository turfRepository;

    public Turf addTurf(Turf turf) {
        return turfRepository.save(turf);
    }

    public List<Turf> getAllTurfs() {
        return turfRepository.findAll();
    }

    public void deleteTurf(Long id) {
        turfRepository.deleteById(id);
    }

    // if(!bookingRepository.findByTurfId(id).isEmpty()) {
    //     return ResponseEntity.badRequest().body("Cannot delete. Active bookings exist.");
    // }
}