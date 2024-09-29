package ru.semavin.app.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.semavin.app.dto.PersonDTO;
import ru.semavin.app.model.Person;
import ru.semavin.app.services.AdminService;
import ru.semavin.app.services.RegisterServices;

import java.util.Collections;
import java.util.Map;
// TODO Валидация
@Controller
@RequestMapping("/login")
public class LoginController {

    private final RegisterServices regService;
    private final AdminService adminService;
    private final ModelMapper modelMapper;
    @Autowired
    public LoginController(RegisterServices regService, AdminService adminService, ModelMapper modelMapper) {
        this.regService = regService;
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/page")
    public String loginPage(){
        return "login/page";
    }
    @GetMapping("/register")
    public String registerPage(@ModelAttribute("person") PersonDTO person){
        return "login/register";
    }
    @PostMapping("/register")
    public String performReg(@ModelAttribute @Valid PersonDTO person,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "login/page";
        }
        System.out.println(person.getUsername());

        regService.register(convertToPerson(person));
        return "redirect:/page";
    }
    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStuff();
        return "login/admin";
    }
    public Person convertToPerson(PersonDTO person){
        return this.modelMapper.map(person, Person.class);
    }



}
