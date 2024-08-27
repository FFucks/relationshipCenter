package com.relationship.center.services;

import com.relationship.center.models.Attendant;
import com.relationship.center.models.Problem;
import com.relationship.center.models.Team;
import com.relationship.center.utils.Validations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CenterApplicationService {

    private final Map<String, Team> teams = new HashMap<>();

    CenterApplicationService() {
        teams.put("Cartões", new Team("Cartões"));
        teams.put("Empréstimo", new Team("Empréstimo"));
        teams.put("Outros", new Team("Outros"));
    }

    public boolean addAttendant(String team, String attendantName) {
        if (!Validations.validateAttendant(team)) {
            return false;
        }

        Attendant attendant = new Attendant(attendantName);
        Team t = teams.get(team);
        if (!t.getQueue().isEmpty()) {
            attendant.addProblem(t.getAndRemoveQueue());
        }
        t.addAttendant(attendant);

        return true;
    }

    public List<Attendant> getAttendants(String teamName) {
        return teams.get(teamName).getAttendants();
    }

    public List<Attendant> getAllAttendants() {
        List<Attendant> attendantList = new ArrayList<>();
        teams.forEach((key, team) -> {
            attendantList.addAll(team.getAttendants());
        });
        return attendantList;
    }

    public String addProblem(String team, String message) {
        if (!Validations.validateAttendant(team)) {
            return "INVALIDO";
        }
        Team t = teams.get(team);

        for (Attendant attendant : t.getAttendants()) {
            if (attendant.verifyMaxProblems()) {
                attendant.addProblem(new Problem(message));
                return "SUCESSO";
            }
        }

        t.addQueue(new Problem(message));
        return "FILA";
    }

    public String deleteProblem(String team, String name) {
        if (!Validations.validateAttendant(team)) {
            return "INVALIDO";
        }
        Team t = teams.get(team);
        for (Attendant attendant: t.getAttendants()) {
            if (attendant.getName().equalsIgnoreCase(name)) {
                if (Objects.nonNull(attendant.getProblems()) && !attendant.getProblems().isEmpty()) {
                    attendant.getProblems().remove(0);
                    addQueuedInProblemList(attendant, t);
                    return "REMOVIDO";
                }
            }
        }

        return "NAO_ENCONTRADO";
    }

    public List<Problem> getAllQueueList() {
        List<Problem> queueList = new ArrayList<>();

        teams.forEach((key, team) -> {
            queueList.addAll(team.getQueue());
        });

        return queueList;
    }

    private void addQueuedInProblemList(Attendant attendant, Team team) {
        if (!team.getQueue().isEmpty()) {
            attendant.addProblem(team.getAndRemoveQueue());
        }
    }

}
