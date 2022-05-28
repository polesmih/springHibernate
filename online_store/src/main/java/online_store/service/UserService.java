package online_store.service;


import online_store.domain.dto.ShopUserDTO;
import online_store.domain.dto.ShopUserRegisterDTO;
import online_store.exception.ShopException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<ShopUserDTO> getAllUsers();
    void addUser(ShopUserRegisterDTO dto) throws ShopException;
    void changeUserStatus(long userId, boolean enabled);
}
