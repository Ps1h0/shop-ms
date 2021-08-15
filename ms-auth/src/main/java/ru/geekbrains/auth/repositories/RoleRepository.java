package ru.geekbrains.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
