package br.com.fiap.ecoMap.controller;


import br.com.fiap.ecoMap.dto.DenunciaCadastroDto;
import br.com.fiap.ecoMap.dto.DenunciaExibicaoDto;
import br.com.fiap.ecoMap.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DenunciaController {
    @Autowired
    private DenunciaService denunciaService;

    @PostMapping("/coletas")
    @ResponseStatus(HttpStatus.CREATED)
    public DenunciaExibicaoDto gravar(@RequestBody @Valid DenunciaCadastroDto denunciaCadastroDto)
    {
        return denunciaService.gravar(denunciaCadastroDto);
    }

    @GetMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public Page<DenunciaExibicaoDto> listarTodasDenuncias(Pageable paginacao){
        return denunciaService.listarTodasDenuncias(paginacao);
    }

    @DeleteMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        denunciaService.excluir(id);
    }

    @PutMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public DenunciaExibicaoDto atualizar(@RequestBody DenunciaCadastroDto denunciaCadastroDto){
        return denunciaService.atualizar(denunciaCadastroDto);
    }

    @GetMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DenunciaExibicaoDto buscarPorId(@PathVariable Long id) {
        return denunciaService.buscarPorId(id);
    }
}
