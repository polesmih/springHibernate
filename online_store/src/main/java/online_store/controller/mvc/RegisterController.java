package online_store.controller.mvc;


import lombok.RequiredArgsConstructor;
import online_store.domain.dto.ShopUserRegisterDTO;
import online_store.service.UserService;
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
