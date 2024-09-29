package ru.semavin.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semavin.app.model.Item;

public interface ItemRepostitory extends JpaRepository<Item, Integer> {

}
