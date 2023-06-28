package seguranca.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seguranca.demo.entity.Usuario;
import seguranca.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Usuario> buscarTodos() {
        return userService.buscarTodosUsuarios();
    }
}
