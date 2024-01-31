package com.nazarov.javaspringbootlessonfour.repositories;

import com.nazarov.javaspringbootlessonfour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
