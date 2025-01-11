package com.belkhiria.mystore.Repositories;

import com.belkhiria.mystore.models.Role;
import com.belkhiria.mystore.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName role);
}

