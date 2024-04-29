package com.amvatui.monolith;

import com.amvatui.monolith.entity.ERole;
import com.amvatui.monolith.entity.Role;
import com.amvatui.monolith.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonolithApplicationRunner implements ApplicationRunner {
    private static final List<ERole> DEFAULT_ROLES = List.of(
            ERole.ROLE_USER, ERole.ROLE_ADMIN, ERole.ROLE_MODERATOR);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        for (ERole role : DEFAULT_ROLES) {
            if (roleRepository.findByRole(role).isEmpty()) {
                roleRepository.save(new Role(null, role));
            }
        }
    }
}
