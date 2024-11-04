package br.com.fiap.ecoMap.service;


import br.com.fiap.ecoMap.dto.LocalizacaoCadastroDto;
import br.com.fiap.ecoMap.dto.LocalizacaoExibicaoDto;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Localizacao;
import br.com.fiap.ecoMap.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalizacaoService {
    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;


    public LocalizacaoExibicaoDto gravar(LocalizacaoCadastroDto localizacaoCadastroDto) {
        Optional<Localizacao> localizacaExistenteOptional = localizacaoRepository.findByCoordenadas(localizacaoCadastroDto.coordenadas());
        Localizacao localizacao;
        if (localizacaExistenteOptional.isPresent()) {
            localizacao = localizacaExistenteOptional.get();
        } else {
            localizacao = new Localizacao();
            BeanUtils.copyProperties(localizacaoCadastroDto, localizacao);
        }

        if (localizacaoCadastroDto.bairro() != null) {
            AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(localizacaoCadastroDto.bairro())
                    .orElseGet(() -> {
                        // Se não encontrar, cria uma nova área mapeada e salva no repositorio
                        AreaMapeada novaAreaMapeada = new AreaMapeada();
                        novaAreaMapeada.setBairro(localizacaoCadastroDto.bairro());
                        return areaMapeadaRepository.save(novaAreaMapeada);
                    });

            localizacao.setAreaMapeada(areaMapeada);
        } else {
            throw new EntityNotFoundException ("Bairro não encontrado.");
        }
        // Salva e retorna a localização encontrada / criada
        return new LocalizacaoExibicaoDto(localizacaoRepository.save(localizacao));
    }






    public LocalizacaoExibicaoDto atualizar(LocalizacaoCadastroDto localizacaoCadastroDto){
        Localizacao localizacao = new Localizacao();
        BeanUtils.copyProperties(localizacaoCadastroDto, localizacao);

        Optional<Localizacao> localizacaoOptional = localizacaoRepository.findById(localizacao.getId());
        if(localizacaoOptional.isPresent()){
            if (localizacaoCadastroDto.bairro() != null) {
                AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(localizacaoCadastroDto.bairro())
                        .orElseThrow(() -> new EntityNotFoundException("Coleta não encontrada"));
                localizacao.setAreaMapeada(areaMapeada);

            }
            return new LocalizacaoExibicaoDto(localizacaoRepository.save(localizacao));
        }
        else {
            throw new EntityNotFoundException("Área não encontrada");        }
    }


    public LocalizacaoExibicaoDto buscarPorId(Long id){
        Optional<Localizacao> localizacaoOptional = localizacaoRepository.findById(id);
        if(localizacaoOptional.isPresent()){
            System.out.println(localizacaoOptional.get());
            return new LocalizacaoExibicaoDto(localizacaoOptional.get());
        }
        else {
            throw new EntityNotFoundException("Localização não encontrada");
        }
    }

    public Page<LocalizacaoExibicaoDto> listarTodasLocalizacoes(Pageable paginacao)
    {
        return localizacaoRepository
                .findAll(paginacao)
                .map(LocalizacaoExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Localizacao> localizacaoOptional = localizacaoRepository.findById(id);
        if(localizacaoOptional.isPresent()){
            localizacaoRepository.delete(localizacaoOptional.get());
        }
        else {
            throw new EntityNotFoundException("Área não encontrada");
        }
    }


}
