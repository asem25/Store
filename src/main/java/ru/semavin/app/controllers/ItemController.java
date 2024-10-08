package ru.semavin.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.semavin.app.services.ItemService;

@Controller
@RequestMapping("/catalog")
public class ItemController {
    //TODO реализовать страинчку для товара со стороны админа(минимализм, возможность редакта)
    //TODO реализовать страничку для пользователя(описание, скидка и тд)
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public String oneItem(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.findOne(id));
        return "item/show";
    }

}
