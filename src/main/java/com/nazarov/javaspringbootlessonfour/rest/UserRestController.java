package com.nazarov.javaspringbootlessonfour.rest;


import com.nazarov.javaspringbootlessonfour.dto.ProductDTO;
import com.nazarov.javaspringbootlessonfour.dto.UserDTO;
import com.nazarov.javaspringbootlessonfour.entities.Product;
import com.nazarov.javaspringbootlessonfour.entities.User;
import com.nazarov.javaspringbootlessonfour.services.UserService;
import com.nazarov.javaspringbootlessonfour.services.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "API to manipulate user resources")
public class UserRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public UserDTO findById(@PathVariable("id") Long id) {
        User user = userService.findById(id).orElseThrow(NotFoundException::new);
        return userDTO(user);
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) {
        userService.createOrUpdate(user);
        return user;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody User user) {
        if (userService.existById(user.getId())) {
            userService.createOrUpdate(user);
            return user;
        }
        throw new NotFoundException();

    }

    @DeleteMapping("{id}/id")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    private UserDTO userDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nickname("Little - " + user.getLogin())
                .productDTOS(toDTOs(user.getProducts()))
                .build();
    }

    private List<ProductDTO> toDTOs(List<Product> products) {
        return products.stream().map(p -> toDTO(p)).collect(Collectors.toList());
    }

    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())

                .build();
    }


}
