package online_store.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShopEntityNotFoundException extends ShopException {

    public ShopEntityNotFoundException() {
        super();
    }

    public ShopEntityNotFoundException(String message) {
        super(message);
    }

    public ShopEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
