package com.nazarov.javaspringbootlessonfour.services;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.nazarov.javaspringbootlessonfour.entities.Product;
import com.nazarov.javaspringbootlessonfour.repositories.ProductDAO;
import com.nazarov.javaspringbootlessonfour.repositories.spocifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }

    @Transactional
    public Optional<Product> getById(Long id) {
        return productDAO.findById(id);
    }

    @Transactional
    public void remove(Long id) {
        productDAO.deleteById(id);
    }

    @Transactional
    public void addOrUpdate(Product product) {
        productDAO.save(product);
    }

    @Transactional
    public Page<Product> getByParams(Optional<String> nameFilter,
                                     Optional<BigDecimal> min,
                                     Optional<BigDecimal> max,
                                     Optional<Integer> page,
                                     Optional<Integer> size,
                                     Optional<String> sortField,
                                     Optional<String> sortOrder) {
        //1 способ
//        if (!nameFilter.contains("%")) {
//            nameFilter = String.join("", "%", nameFilter, "%");
//        }
//        return productDAO.findProductByTitleLike(nameFilter);

        //2 способ
        Specification<Product> specification = Specification.where(null);
        if (nameFilter.isPresent()) {
            specification = specification.and(ProductSpecification.titleLike(nameFilter.get()));
        }

        if (min.isPresent()) {
            specification = specification.and(ProductSpecification.greaterOrEquals(min.get()));
        }

        if (max.isPresent()) {
            specification = specification.and(ProductSpecification.lessOrEquals(max.get()));
        }

        if (sortField.isPresent() && sortOrder.isPresent()) {
            productDAO.findAll(specification, PageRequest.of(page.orElse(1) - 1, size.orElse(4),
                    Sort.by(Sort.Direction.fromString(sortOrder.get()), sortField.get())));
        }

        return productDAO.findAll(specification, PageRequest.of(page.orElse(1) - 1, size.orElse(6)));


    }

//    @Transactional
//    public List<Product> getMaxProduct(int maxPrice) {
//        Specification<Product >
//    }
//
//    @Transactional
//    public List<Product> getMinProduct(int minPrice) {
//        productDAO
//    }

}
