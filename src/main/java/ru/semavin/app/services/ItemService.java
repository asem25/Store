package ru.semavin.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.semavin.app.model.Item;
import ru.semavin.app.repositories.ItemRepostitory;
import ru.semavin.app.util.ItemNotFoundException;

import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepostitory itemRepostitory;
    @Autowired
    public ItemService(ItemRepostitory itemRepostitory) {
        this.itemRepostitory = itemRepostitory;
    }

    public List<Item> findAll(){
        return itemRepostitory.findAll();
    }

    public void addOne(Item item) {
        itemRepostitory.save(item);
    }

    public Item findOne(int id) {
        return itemRepostitory.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with this id not founded"));
    }
}
