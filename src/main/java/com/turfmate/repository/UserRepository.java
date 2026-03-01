package com.turfmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turfmate.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);
}