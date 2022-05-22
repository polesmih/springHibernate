package com.example.hibernate.service;


import com.example.hibernate.domain.dto.ShopUserDTO;
import com.example.hibernate.domain.dto.ShopUserRegisterDTO;
import com.example.hibernate.exception.ShopException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<ShopUserDTO> getAllUsers();
    void addUser(ShopUserRegisterDTO dto) throws ShopException;
    void changeUserStatus(long userId, boolean enabled);
}
