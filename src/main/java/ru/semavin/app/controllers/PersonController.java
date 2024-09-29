package ru.semavin.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.semavin.app.dto.PersonProfileDTO;
import ru.semavin.app.model.Person;
import ru.semavin.app.services.PersonService;
import ru.semavin.app.util.PersonNotFoundException;

@Controller
@RequestMapping()
//TODO Добавить валидацию
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/profile")
    public String viewProfile(Model model){
        PersonProfileDTO person = personService.getCurrentUserProfile();
        model.addAttribute("person", person);
        return "person/profile";
    }


}
