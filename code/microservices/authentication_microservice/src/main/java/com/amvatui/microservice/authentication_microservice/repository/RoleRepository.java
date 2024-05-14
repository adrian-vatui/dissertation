package com.amvatui.microservice.authentication_microservice.repository;

import com.amvatui.microservice.authentication_microservice.entity.ERole;
import com.amvatui.microservice.authentication_microservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(ERole role);
}
