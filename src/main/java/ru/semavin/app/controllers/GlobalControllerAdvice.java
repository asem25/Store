package ru.semavin.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.semavin.app.util.PersonNotFoundException;

@ControllerAdvice(basePackages = "ru.semavin.app.controllers")
public class GlobalControllerAdvice {
    @ExceptionHandler(PersonNotFoundException.class)
    //TODO Сделать вывод страницы ошибки error/person-not-found
    public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException personNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personNotFoundException.getMessage());
    }
}
