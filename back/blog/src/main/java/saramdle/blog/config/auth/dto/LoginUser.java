package saramdle.blog.config.auth.dto;

import java.io.Serializable;
import lombok.Getter;
import saramdle.blog.domain.User;

@Getter
public class LoginUser implements Serializable {
    private String email;
    private String name;
    private String picture;

    public LoginUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.picture = user.getPicture();
    }
}
