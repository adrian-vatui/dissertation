package com.amvatui.monolith.repository;

import com.amvatui.monolith.entity.ERole;
import com.amvatui.monolith.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(ERole role);
}
