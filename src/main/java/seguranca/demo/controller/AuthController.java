package seguranca.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seguranca.demo.entity.Usuario;
import seguranca.demo.jwt.JwtRequest;
import seguranca.demo.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UsuarioService userService;
 
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest request) {

        List<Usuario> uList = userService.buscarTodosUsuarios();
        
        return userService.signin(request);

    }
}
