package saramdle.blog.config.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;
import saramdle.blog.config.auth.dto.LoginUser;

public class CustomUserPrinciple implements OAuth2User, Serializable {

    private LoginUser loginUser;
    private Set<GrantedAuthority> authorities;
    private Map<String, Object> oAuthAttributes;

    public CustomUserPrinciple(LoginUser loginUser, Collection<? extends GrantedAuthority> authorities, Map<String, Object> oAuthAttributes) {
        this.loginUser = loginUser;
        this.authorities = (authorities != null)
                ? Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)))
                : Collections.unmodifiableSet(new LinkedHashSet<>(AuthorityUtils.NO_AUTHORITIES));
        this.oAuthAttributes = oAuthAttributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oAuthAttributes);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableSet(authorities);
    }

    @Override
    public String getName() {
        return loginUser.getEmail();
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                Comparator.comparing(GrantedAuthority::getAuthority));
        sortedAuthorities.addAll(authorities);
        return sortedAuthorities;
    }
}
