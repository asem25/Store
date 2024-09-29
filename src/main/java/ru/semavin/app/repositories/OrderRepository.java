package ru.semavin.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semavin.app.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Override
    List<Order> findAll();

    @Override
    List<Order> findAllById(Iterable<Integer> integers);

}
