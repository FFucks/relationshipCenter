package com.relationship.center.models;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private List<Attendant> attendants = new ArrayList<>();
    private List<Problem> queue = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addAttendant(Attendant attendant) {
        this.attendants.add(attendant);
    }

    public void addQueue(Problem queue) {
        this.queue.add(queue);
    }

    public Problem getAndRemoveQueue() {
        if (!this.queue.isEmpty()) {
            Problem problem = queue.get(0);
            queue.remove(0);
            return problem;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(List<Attendant> attendants) {
        this.attendants = attendants;
    }

    public List<Problem> getQueue() {
        return queue;
    }

    public void setQueue(List<Problem> queue) {
        this.queue = queue;
    }
}
