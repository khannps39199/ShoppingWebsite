package poly.edu.Entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
@Data

public class UserDetail implements UserDetails {
    private final User user;

    public UserDetail(User user) {
    	System.out.println(user.getRole());
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));  // Ensure the prefix "ROLE_" is here
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public User getUser() {
        return user;
    }
}