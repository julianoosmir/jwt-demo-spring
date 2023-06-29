package seguranca.demo.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seguranca.demo.model.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private List<Role> role;
    private boolean sign;
    private String token;
}
