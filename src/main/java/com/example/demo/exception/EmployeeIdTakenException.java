package com.example.demo.exception;

public class EmployeeIdTakenException extends RuntimeException {
    public EmployeeIdTakenException(Long id) {
        super("Already exists employee with id " + id);
    }
}
