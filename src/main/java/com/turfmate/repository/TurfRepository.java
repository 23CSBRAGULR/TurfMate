package com.turfmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turfmate.entity.Turf;

public interface TurfRepository extends JpaRepository<Turf, Long> {
}
