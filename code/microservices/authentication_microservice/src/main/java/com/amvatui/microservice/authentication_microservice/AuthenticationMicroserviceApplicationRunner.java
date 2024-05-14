package com.amvatui.microservice.authentication_microservice;

import com.amvatui.microservice.authentication_microservice.entity.ERole;
import com.amvatui.microservice.authentication_microservice.entity.Role;
import com.amvatui.microservice.authentication_microservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationMicroserviceApplicationRunner implements ApplicationRunner {
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
