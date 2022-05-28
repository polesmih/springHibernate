package online_store.service.impl;


import lombok.RequiredArgsConstructor;
import online_store.domain.Role;
import online_store.repository.RoleRepository;
import online_store.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
