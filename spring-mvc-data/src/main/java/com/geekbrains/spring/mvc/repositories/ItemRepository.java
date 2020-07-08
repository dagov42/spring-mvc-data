package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    Item findOneByName(String name);
    List<Item> findAllByPriceGreaterThan(int minPrice);
}