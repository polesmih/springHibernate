package com.example.hibernate.domain.dto;


import com.example.hibernate.domain.ShopUser;
import lombok.Data;

@Data
public class ShopUserDTO {
    private long id;
    private String login;
    private String email;
    private boolean enabled;

    public ShopUserDTO(ShopUser that) {
        id = that.getId();
        login = that.getLogin();
        enabled = that.isEnabled();
        email = that.getEmail();
    }
}
