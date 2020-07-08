package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.exceptions.ItemNotFoundException;
import com.geekbrains.spring.mvc.model.Item;
import com.geekbrains.spring.mvc.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item saveOrUpdate(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Can't found item with id = " + id));
    }

    public Item findByName(String name) {
        return itemRepository.findOneByName(name);
    }

    public List<Item> findByMinPrice(int minPrice) {
        return itemRepository.findAllByPriceGreaterThan(minPrice);
    }

    public Page<Item> findByPage(int pageNumber, int pageSize) {
        return itemRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Page<Item> findAll(Specification<Item> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return itemRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }
}
