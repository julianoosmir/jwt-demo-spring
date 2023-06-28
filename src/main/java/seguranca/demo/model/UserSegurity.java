package seguranca.demo.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSegurity implements UserDetails {

    private String username;

    private String password;

    private Boolean ativo;

    List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getAtivo();
    }

    @Override
    public boolean isAccountNonLocked() {
      return getAtivo();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getAtivo();
    }

    @Override
    public boolean isEnabled() {
        return getAtivo();
    }
}
