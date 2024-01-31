package com.nazarov.javaspringbootlessonfour.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String login;
    private String nickname;
    private List<ProductDTO> productDTOS;
}
