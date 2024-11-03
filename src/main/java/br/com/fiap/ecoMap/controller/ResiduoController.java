package br.com.fiap.ecoMap.controller;

import br.com.fiap.ecoMap.dto.ResiduoCadastroDto;
import br.com.fiap.ecoMap.dto.ResiduoExibicaoDto;
import br.com.fiap.ecoMap.service.ResiduoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResiduoController {
    @Autowired
    private ResiduoService residuoService;

    @PostMapping("/coletas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResiduoExibicaoDto gravar(@RequestBody @Valid ResiduoCadastroDto residuoCadastroDto)
    {
        return residuoService.gravar(residuoCadastroDto);
    }

    @GetMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResiduoExibicaoDto> listarTodosResiduos(Pageable paginacao){
        return residuoService.listarTodosResiduos(paginacao);
    }

    @DeleteMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        residuoService.excluir(id);
    }

    @PutMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public ResiduoExibicaoDto atualizar(@RequestBody ResiduoCadastroDto residuoCadastroDto){
        return residuoService.atualizar(residuoCadastroDto);
    }

    @GetMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResiduoExibicaoDto buscarPorId(@PathVariable Long id) {
        return residuoService.buscarPorId(id);
    }
}
