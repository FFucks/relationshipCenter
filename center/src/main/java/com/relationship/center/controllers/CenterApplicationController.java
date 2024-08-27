package com.relationship.center.controllers;

import com.relationship.center.models.*;
import com.relationship.center.services.CenterApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CenterApplicationController {

    @Autowired
    private CenterApplicationService service;

    @PostMapping("/attendant")
    public ResponseEntity<MessageReturn> postAttendant(@RequestBody AttendantBody attendantBody) {
        boolean response = service.addAttendant(attendantBody.getTeam(), attendantBody.getName());

        return response ?
                new ResponseEntity<>(new MessageReturn(HttpStatus.CREATED.value(), "Criado com sucesso"), HttpStatus.CREATED)
                : new ResponseEntity<>(new MessageReturn(HttpStatus.BAD_REQUEST.value(), "Tipo de atendente inválido"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/attendants")
    public ResponseEntity<List<Attendant>> getAttendants(@RequestHeader("tipo") String tipeAttendant) {
        List<Attendant> response = service.getAttendants(tipeAttendant);

        return response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allAttendants")
    public ResponseEntity<List<Attendant>> getAllAttendants() {
        List<Attendant> response = service.getAllAttendants();

        return response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/problem")
    public ResponseEntity<MessageReturn> postProblem(@RequestBody ProblemBody problemBody) {
        String response = service.addProblem(problemBody.getTeam(), problemBody.getMessage());

        if ("INVALIDO".equalsIgnoreCase(response)) {
            return new ResponseEntity<>(new MessageReturn(HttpStatus.BAD_REQUEST.value(), "Tipo de atendente inválido"), HttpStatus.BAD_REQUEST);
        } else if ("SUCESSO".equalsIgnoreCase(response)) {
            return new ResponseEntity<>(new MessageReturn(HttpStatus.CREATED.value(), "Criado com sucesso"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageReturn(HttpStatus.CREATED.value(), "Adicionado à fila"), HttpStatus.CREATED);
    }

    @DeleteMapping("/problem")
    public ResponseEntity<MessageReturn> deleteProblem(@RequestBody ProblemBody problemBody) {
        String response = service.deleteProblem(problemBody.getTeam(), problemBody.getName());

        if ("INVALIDO".equalsIgnoreCase(response)) {
            return new ResponseEntity<>(new MessageReturn(HttpStatus.BAD_REQUEST.value(), "Tipo de atendente inválido"), HttpStatus.BAD_REQUEST);
        } else if ("REMOVIDO".equalsIgnoreCase(response)) {
            return new ResponseEntity<>(new MessageReturn(HttpStatus.OK.value(), "Removido com sucesso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageReturn(HttpStatus.NOT_FOUND.value(), "Atendente não encontrado"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allQueues")
    public ResponseEntity<List<Problem>> getAllQueueList() {
        List<Problem> response = service.getAllQueueList();

        return response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

}
