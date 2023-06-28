package seguranca.demo.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    
}
