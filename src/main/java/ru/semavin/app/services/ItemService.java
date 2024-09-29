package ru.semavin.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.semavin.app.repositories.ItemRepostitory;

@Service
@Transactional
public class ItemService {
    private final ItemRepostitory itemRepostitory;
    @Autowired
    public ItemService(ItemRepostitory itemRepostitory) {
        this.itemRepostitory = itemRepostitory;
    }



}
