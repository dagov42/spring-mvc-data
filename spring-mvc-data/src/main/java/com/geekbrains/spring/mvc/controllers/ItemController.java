package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Item;
import com.geekbrains.spring.mvc.repositories.specifications.ItemSpecifications;
import com.geekbrains.spring.mvc.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// root: http://localhost:8189/app/items

@Controller
@RequestMapping("/items")
public class ItemController {
    private ItemsService itemsService;

    @Autowired
    public ItemController(ItemsService itemsService) {
        this.itemsService = this.itemsService;
    }

    @GetMapping
    public String showAllItems(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                               @RequestParam(name = "min_price", required = false) Integer minPrice,
                               @RequestParam(name = "max_price", required = false) Integer maxPrice) {
        Specification<Item> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ItemSpecifications.priceGEThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ItemSpecifications.priceLEThan(maxPrice));
        }

        List<Item> items = itemsService.findAll(spec, pageNumber).getContent();
        model.addAttribute("items", items);
        return "all_items";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_item_form";
    }

    @PostMapping("/add")
    public String saveNewItem(@ModelAttribute Item newItem) {
        itemsService.saveOrUpdate(newItem);
        return "redirect:/items/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("items", itemsService.findById(id));
        return "edit_item_form";
    }

    @PostMapping("/edit")
    public String modifyItem(@ModelAttribute Item modifiedItem) {
        itemsService.saveOrUpdate(modifiedItem);
        return "redirect:/students/";
    }

    @GetMapping("/info_by_name")
    @ResponseBody
    public Item infoByName(@RequestParam String name) {
        return itemsService.findByName(name);
    }

    @GetMapping("/find_by_min_price")
    @ResponseBody
    public List<Item> findItemsByMinPrice(@RequestParam int price) {
        return itemsService.findByMinPrice(price);
    }
}