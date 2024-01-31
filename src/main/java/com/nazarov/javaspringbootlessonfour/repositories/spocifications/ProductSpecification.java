package com.nazarov.javaspringbootlessonfour.repositories.spocifications;

import com.nazarov.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> titleLike(String titleFilter) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
    }

    //Ищем минимум, больше либо равно
    public static Specification<Product> greaterOrEquals(BigDecimal min) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), min);

    }

    //Ищем максимум, меньше либо равно
    public static Specification<Product> lessOrEquals(BigDecimal max) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), max);

    }


}
