package com.geekbrains.spring.mvc.repositories.specifications;

import com.geekbrains.spring.mvc.model.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecifications {
    public static Specification<Item> priceGEThan(int minPrice) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Item> priceLEThan(int maxPrice) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
