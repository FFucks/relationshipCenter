package com.relationship.center.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Attendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "attendant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problems = new ArrayList<>();

    private static final int MAX_QUEUE = 3;

    public Attendant(String name) {
        this.name = name;
        this.problems = new ArrayList<>();
    }

    public Attendant() {
    }

    public void addProblem(Problem problem) {
        problems.add(problem);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public boolean verifyMaxProblems() {
        return this.problems.size() < MAX_QUEUE;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
