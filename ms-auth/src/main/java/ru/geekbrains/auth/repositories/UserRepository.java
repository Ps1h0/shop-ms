package ru.geekbrains.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.auth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
