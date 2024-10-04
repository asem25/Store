package ru.semavin.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.semavin.app.services.ItemService;
import ru.semavin.app.services.OrderService;

@Controller

public class MainController {
    private  final ItemService itemService;
    @Autowired
    public MainController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String HomePage(Model model){
        model.addAttribute("items", itemService.findAll());
        return "home/home";
    }
}
