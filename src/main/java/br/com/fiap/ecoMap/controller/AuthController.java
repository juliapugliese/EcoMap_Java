package br.com.fiap.ecoMap.controller;

import br.com.fiap.ecoMap.config.security.TokenService;
import br.com.fiap.ecoMap.dto.LoginDto;
import br.com.fiap.ecoMap.dto.TokenDto;
import br.com.fiap.ecoMap.dto.UsuarioCadastroDto;
import br.com.fiap.ecoMap.dto.UsuarioExibicaoDto;
import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
//controlador que vai tentar autenticar o ususario no sistema
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto login(@RequestBody @Valid LoginDto loginDto
    //@RequestBody o objeto vai estar no corpo da requisicao
    ){
        UsernamePasswordAuthenticationToken userNamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.senha()
                );
        //userNamePassword = objeto que tem o usuario e a senha que vai se autenticar

        Authentication auth = authenticationManager.authenticate(userNamePassword);
        //auth = obj authentication que de fato vai gerenciar a autenticaco

        String  token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return new TokenDto(token);
    }

    //cadastrando novo usuario
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        UsuarioExibicaoDto usuariosalvo = null;
        usuariosalvo = usuarioService.salvarUsuario(usuarioCadastroDto);
        return usuariosalvo;
    }
}
