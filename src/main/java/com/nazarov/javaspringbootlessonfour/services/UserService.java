package com.nazarov.javaspringbootlessonfour.services;

import com.nazarov.javaspringbootlessonfour.entities.User;
import com.nazarov.javaspringbootlessonfour.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User createOrUpdate(User user) {
        return repository.save(user);
    }


    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }


    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public boolean existById(Long id) {
        return repository.existsById(id);
    }
}
