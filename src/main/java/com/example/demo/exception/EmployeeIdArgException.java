package com.example.demo.exception;

public class EmployeeIdArgException extends RuntimeException {
    public EmployeeIdArgException() {
        super("Cannot create or change an employee id!");
    }
}
