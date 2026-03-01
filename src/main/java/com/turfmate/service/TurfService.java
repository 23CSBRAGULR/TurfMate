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
}