package online_store.domain.dto;


import lombok.Data;
import online_store.domain.ShopUser;

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
