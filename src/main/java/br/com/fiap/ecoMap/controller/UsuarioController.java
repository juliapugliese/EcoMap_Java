package br.com.fiap.ecoMap.controller;

import br.com.fiap.ecoMap.dto.UsuarioCadastroDto;
import br.com.fiap.ecoMap.dto.UsuarioExibicaoDto;
import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto salvar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto)
    {
        return usuarioService.salvarUsuario(usuarioCadastroDto);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDto> litarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarUsuarioPorId(@PathVariable Long usuarioId){
        return usuarioService.buscarPorId(usuarioId);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){
        return usuarioService.atualizar(usuario);
    }


}

