package com.example.hibernate.service.impl;

import com.example.hibernate.domain.Role;
import com.example.hibernate.domain.ShopUser;
import com.example.hibernate.domain.dto.ShopUserDTO;
import com.example.hibernate.domain.dto.ShopUserRegisterDTO;
import com.example.hibernate.exception.ShopException;
import com.example.hibernate.repository.ShopUserRepository;
import com.example.hibernate.service.RoleService;
import com.example.hibernate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ShopUserRepository shopUserRepository;
    private final RoleService roleService;


    private Optional<ShopUser> findByUsername(String login) {
        return shopUserRepository.findShopUserByLogin(login);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return findByUsername(login)
                .filter(ShopUser::isEnabled)
                .map(su -> new User(su.getLogin(), su.getPassword(), mapRolesToAuthorities(su.getRoles())))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", login)));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<ShopUserDTO> getAllUsers() {
        return shopUserRepository.findAll().stream().map(ShopUserDTO::new).collect(Collectors.toList());
    }

    @Override
    public void addUser(ShopUserRegisterDTO dto) throws ShopException {
        ShopUser shopUser = ShopUser.builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .enabled(true)
                .roles(List.of(roleService.findRoleByName("ROLE_USER").orElseThrow(()-> new ShopException("Проблемы на сервере"))))
                .build();
        shopUserRepository.saveAndFlush(shopUser);

    }

    @Override
    public void changeUserStatus(long userId, boolean enabled) throws ShopException {
        ShopUser shopUser = shopUserRepository.findById(userId).orElseThrow(() -> new ShopException("Пользователь не найден"));
        shopUser.setEnabled(enabled);
        shopUserRepository.saveAndFlush(shopUser);
    }
}