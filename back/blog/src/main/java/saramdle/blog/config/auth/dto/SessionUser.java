package saramdle.blog.config.auth.dto;

import java.io.Serializable;
import lombok.Getter;
import saramdle.blog.domain.User;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String name;
    private String picture;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.picture = user.getPicture();
    }
}
