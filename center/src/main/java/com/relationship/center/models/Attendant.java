package com.relationship.center.models;

import java.util.ArrayList;
import java.util.List;

public class Attendant {

    private String name;
    private List<Problem> problems;

    private static final int MAX_QUEUE = 3;

    public Attendant(String name) {
        this.name = name;
        this.problems = new ArrayList<>();
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
}
