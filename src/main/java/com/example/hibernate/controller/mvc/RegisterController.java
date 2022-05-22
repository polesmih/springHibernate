package com.example.hibernate.controller.mvc;


import com.example.hibernate.domain.dto.ShopUserRegisterDTO;
import com.example.hibernate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("registerData", new ShopUserRegisterDTO());
        return "users/register";
    }

    @PostMapping
    public String addUser(ShopUserRegisterDTO dto) {
        userService.addUser(dto);
        return "redirect:/product";
    }

}
