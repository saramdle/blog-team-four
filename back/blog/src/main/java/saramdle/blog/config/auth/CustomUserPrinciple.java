package saramdle.blog.config.auth;

import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import saramdle.blog.config.auth.dto.LoginUser;
import saramdle.blog.config.auth.dto.OAuthAttributes;
import saramdle.blog.domain.User;

@Getter
public class CustomUserPrinciple extends DefaultOAuth2User {

    private LoginUser loginUser;

    public CustomUserPrinciple(User user, OAuthAttributes attributes) {
        super(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
        this.loginUser = new LoginUser(user);
    }

    public String getUserEmail() {
        return loginUser.getEmail();
    }
}
