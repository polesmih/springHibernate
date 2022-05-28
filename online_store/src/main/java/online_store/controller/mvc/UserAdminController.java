package online_store.controller.mvc;


import lombok.RequiredArgsConstructor;
import online_store.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static online_store.util.ShopConstants.ADMIN_URL;

@Controller
@RequestMapping(ADMIN_URL + "/user")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users_list";
    }

    @GetMapping("/change_status")
    public String changeStatus(@RequestParam(name = "id") long id,
                               @RequestParam(name = "enabled") boolean enabled) {
        userService.changeUserStatus(id, enabled);
        return String.format("redirect:%s/user/list", ADMIN_URL);
    }

}
