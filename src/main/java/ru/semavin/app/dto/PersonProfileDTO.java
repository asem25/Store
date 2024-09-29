package ru.semavin.app.dto;

import lombok.Getter;
import lombok.Setter;
import ru.semavin.app.model.Order;

import java.util.List;

@Getter
@Setter
public class PersonProfileDTO {
    private int id ;
    private String username;
    private String email;
    private long balance;
    private List<Order> Orders;
}
