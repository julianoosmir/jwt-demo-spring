package seguranca.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import seguranca.demo.entity.Perfil;
import seguranca.demo.entity.Usuario;
import seguranca.demo.jwt.JwtRequest;
import seguranca.demo.jwt.JwtTokenUtil;
import seguranca.demo.model.Role;
import seguranca.demo.model.UserSegurity;
import seguranca.demo.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationManager authenticationManager;

    public UserDetails loadUserByUsername(String username) {
        final UserDetails userDetails = loadUserSegurityByUsername(username);
        return userDetails;
    }

    private UserSegurity loadUserSegurityByUsername(String username) {
        Usuario usuario = usuarioRepository.findByNome(username);

        List<Role> roles = getRolesPefil(usuario.getPerfils());

        return UserSegurity.builder()
                .roles(roles)
                .ativo(usuario.getAtivo())
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .build();

    }

    private List<Role> getRolesPefil(List<Perfil> perfils) {
        return perfils.stream().map(p -> Role.builder().name(p.getName()).build())
                .collect(Collectors.toList());
    }

    public List<Usuario> buscarTodosUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public ResponseEntity<?> signin(JwtRequest authenticationRequest) {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserSegurity userSegurity = loadUserSegurityByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userSegurity);

            final AuthDto authDto = new AuthDto((userSegurity.getRoles()), true, token);

            return ResponseEntity.ok(authDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIAIS INVALIDAS", e);
        } catch (InternalAuthenticationServiceException e) {
            throw new Exception("USUARIO NÃO CADASTRADO", e);
        }
    }

}
