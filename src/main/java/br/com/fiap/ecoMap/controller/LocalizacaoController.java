package br.com.fiap.ecoMap.controller;


import br.com.fiap.ecoMap.dto.ColetaCadastroDto;
import br.com.fiap.ecoMap.dto.ColetaExibicaoDto;
import br.com.fiap.ecoMap.dto.LocalizacaoCadastroDto;
import br.com.fiap.ecoMap.dto.LocalizacaoExibicaoDto;
import br.com.fiap.ecoMap.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocalizacaoController {
    @Autowired
    private LocalizacaoService localizacaoService;

    @PostMapping("/localizacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public LocalizacaoExibicaoDto gravar(@RequestBody @Valid LocalizacaoCadastroDto localizacaoCadastroDto)
    {
        return localizacaoService.gravar(localizacaoCadastroDto);
    }

    @GetMapping("/localizacoes")
    @ResponseStatus(HttpStatus.OK)
    public Page<LocalizacaoExibicaoDto> listarTodasLocalizacoes(Pageable paginacao){
        return localizacaoService.listarTodasLocalizacoes(paginacao);
    }

    @DeleteMapping("/localizacoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        localizacaoService.excluir(id);
    }

    @PutMapping("/localizacoes")
    @ResponseStatus(HttpStatus.OK)
    public LocalizacaoExibicaoDto atualizar(@RequestBody LocalizacaoCadastroDto localizacaoCadastroDto){
        return localizacaoService.atualizar(localizacaoCadastroDto);
    }

    @GetMapping("/localizacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocalizacaoExibicaoDto buscarPorId(@PathVariable Long id) {

        return localizacaoService.buscarPorId(id);
    }
}
