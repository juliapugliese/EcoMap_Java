package br.com.fiap.ecoMap.config.security;

import br.com.fiap.ecoMap.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerifyToken extends OncePerRequestFilter
//OncePerRequestFilter = garantir que a verificacao vai ser feita somente uma vez por filtragem
{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = "";
        //guardar o toke que vai ser extraido da requisicao

        if (authorizationHeader == null){
            token = null;
        } else {
            token = authorizationHeader.replace("Bearer", "").trim();
            String login = tokenService.validarToken(token);
            UserDetails usuario = usuarioRepository.findByEmail(login);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuario,
                            null,
                            usuario.getAuthorities()
                    );
                    //passar usuario, credeciais, permissoes desse usuario

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //pegou o contexto da aplicacao e setou a autenticacao
            //aplicacao agora conhece o ususario que esta fazendo a solicitacao
        }
        filterChain.doFilter(request, response);
    }
}
