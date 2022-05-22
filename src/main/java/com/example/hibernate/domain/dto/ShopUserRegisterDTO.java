package com.example.hibernate.domain.dto;

import lombok.Data;

@Data
public class ShopUserRegisterDTO {

    private String login;
    private String password;
    private String email;

}
