package ru.semavin.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter @Setter
public class OrderDTO {
    private int id;  // ID заказа
    private int personId;  // ID человека, который сделал заказ
    private List<Integer> itemIds;  // Список ID предметов в заказе
    private LocalDateTime orderDate;  // Дата заказа
}
