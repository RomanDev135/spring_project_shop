package com.nazarov.javaspringbootlessonfour.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
}
