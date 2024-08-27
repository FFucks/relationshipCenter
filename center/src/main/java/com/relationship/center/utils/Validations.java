package com.relationship.center.utils;

public  class Validations {

    public static boolean validateAttendant(String attendant) {
        return "Cartões".equalsIgnoreCase(attendant) || "Empréstimo".equalsIgnoreCase(attendant) || "Outros".equalsIgnoreCase(attendant);
    }
}
