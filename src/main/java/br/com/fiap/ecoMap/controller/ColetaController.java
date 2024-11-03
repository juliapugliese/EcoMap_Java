package br.com.fiap.ecoMap.controller;


import br.com.fiap.ecoMap.dto.ColetaCadastroDto;
import br.com.fiap.ecoMap.dto.ColetaExibicaoDto;
import br.com.fiap.ecoMap.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ColetaController {
    @Autowired
    private ColetaService coletaService;

    @PostMapping("/coletas")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaExibicaoDto gravar(@RequestBody @Valid ColetaCadastroDto coletaCadastroDto)
    {
        return coletaService.gravar(coletaCadastroDto);
    }

    @GetMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public Page<ColetaExibicaoDto> listarTodasColetas(Pageable paginacao){
        return coletaService.listarTodasColetas(paginacao);
    }

    @DeleteMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        coletaService.excluir(id);
    }

    @PutMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public ColetaExibicaoDto atualizar(@RequestBody ColetaCadastroDto coletaCadastroDto){
        return coletaService.atualizar(coletaCadastroDto);
    }

    @GetMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaExibicaoDto buscarPorId(@PathVariable Long id) {
        return coletaService.buscarPorId(id);
    }
}
