package com.relationship.center.repository;

import com.relationship.center.models.Attendant;
import com.relationship.center.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

}
