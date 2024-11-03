package br.com.fiap.ecoMap.controller;

import br.com.fiap.ecoMap.dto.AreaMapeadaCadastroDto;
import br.com.fiap.ecoMap.dto.AreaMapeadaExibicaoDto;
import br.com.fiap.ecoMap.service.AreaMapeadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AreaMapeadaController {
    @Autowired
    private AreaMapeadaService areaService;

    @PostMapping("/areas")
    @ResponseStatus(HttpStatus.CREATED)
    public AreaMapeadaExibicaoDto gravar(@RequestBody @Valid AreaMapeadaCadastroDto areaCadastroDto)
    {
        return areaService.gravar(areaCadastroDto);
    }

    @GetMapping("/areas")
    @ResponseStatus(HttpStatus.OK)
    public Page<AreaMapeadaExibicaoDto> listarTodasAreas(Pageable paginacao){
        return areaService.listarTodasAreas(paginacao);
    }

    @DeleteMapping("/areas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        areaService.excluir(id);
    }

    @PutMapping("/areas")
    @ResponseStatus(HttpStatus.OK)
    public AreaMapeadaExibicaoDto atualizar(@RequestBody AreaMapeadaCadastroDto areaCadastroDto){
        return areaService.atualizar(areaCadastroDto);
    }

    @GetMapping("/areas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AreaMapeadaExibicaoDto buscarPorId(@PathVariable Long id) {
        return areaService.buscarPorId(id);
    }
}
