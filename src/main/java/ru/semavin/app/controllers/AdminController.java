package ru.semavin.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.semavin.app.model.Person;
import ru.semavin.app.services.PersonService;
import ru.semavin.app.util.PersonNotFoundException;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final PersonService personService;
    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable int id, Model model){
        Person person = personService.findById(id).
                orElseThrow(() -> new PersonNotFoundException(String.format("Person with id - %d not found", id)));
        model.addAttribute("person", person);
        return "person/show";
    }

    @GetMapping("/users")
    public String showAll(Model model){
        model.addAttribute("people", personService.findAll());
        return "person/showAll";
    }
    @GetMapping("/users/new")
    public String newPerson(@ModelAttribute("person")Person person){
        return "person/new";
    }
    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") Integer id){
        personService.edit(id, person);
        return "redirect:/profile";
    }
    @PostMapping
    public String create(@ModelAttribute("person") Person person){
        personService.save(person);
        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findById(id));
        return "person/edit";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);

        return "redirect:/";
    }
}
