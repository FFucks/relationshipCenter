package com.relationship.center.repository;

import com.relationship.center.models.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendantRepository extends JpaRepository<Attendant, Long> {
    List<Attendant> findByName(String name);
}
