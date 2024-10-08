package ru.semavin.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.semavin.app.model.Person;
import ru.semavin.app.services.ItemService;
import ru.semavin.app.services.PersonService;
import ru.semavin.app.services.RegisterServices;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    //TODO Создание товара(написание описания)
    private final PersonService personService;
    private final RegisterServices registerServices;
    private final ItemService itemService;
    @Autowired
    public AdminController(PersonService personService, RegisterServices registerServices, ItemService itemService) {
        this.personService = personService;
        this.registerServices = registerServices;
        this.itemService = itemService;
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable int id, Model model){
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "person/show";
    }

    @GetMapping("/users")
    public String showAllPersons(Model model) {
        model.addAttribute("people", personService.findAll());
        return "person/showAll";
    }
    @GetMapping("/users/new")
    public String newPerson(@ModelAttribute("person")Person person){
        return "person/new";
    }
    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") Integer id,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:person/edit";
        }
        personService.edit(id, person);
        return "redirect:profile";
    }
    @PostMapping("/users/person/new")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:person/new";
        }
        person.setPass("1234");
        registerServices.register(person, true);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findById(id));
        return "person/edit";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:admin/users";
    }
    @GetMapping("/users/person/new")
    public String newCreatePage(@ModelAttribute("person") Person person, Model model){
        model.addAttribute("roles", List.of("ROLE_USER", "ROLE_ADMIN"));
        return "person/new";
    }

    @GetMapping("/items")
    public String showAllItems(Model model) {
        model.addAttribute("itemList", itemService.findAll());
        return "item/showAll";
    }
}
