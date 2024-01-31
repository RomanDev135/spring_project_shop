package com.nazarov.javaspringbootlessonfour.repositories;


import com.nazarov.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
//    List<Product> findProductByTitleLike(String title);
}

