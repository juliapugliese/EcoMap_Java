package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.DenunciaCadastroDto;
import br.com.fiap.ecoMap.dto.DenunciaExibicaoDto;
import br.com.fiap.ecoMap.exception.DenunciaNaoEncontradaException;
import br.com.fiap.ecoMap.exception.UsuarioNaoEncontradoException;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.Localizacao;
import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.repository.AreaMapeadaRepository;
import br.com.fiap.ecoMap.repository.DenunciaRepository;
import br.com.fiap.ecoMap.repository.LocalizacaoRepository;
import br.com.fiap.ecoMap.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;

    public DenunciaExibicaoDto gravar(DenunciaCadastroDto denunciaCadastroDto) {
        Denuncia denuncia = new Denuncia();
        BeanUtils.copyProperties(denunciaCadastroDto, denuncia);

        Localizacao localizacao = new Localizacao();
        BeanUtils.copyProperties(denunciaCadastroDto.localizacao(), localizacao);

        //Ver se localização esta salva no repositorio
        Localizacao localizacaoVerificada = localizacaoRepository.findByCoordenadas(localizacao.getCoordenadas())
                .orElseGet(() -> {
                    return localizacao;
                });

        if (denunciaCadastroDto.localizacao().bairro() != null) {
            AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(denunciaCadastroDto.localizacao().bairro())
                    .orElseGet(() -> {
                        // Se não encontrar, cria uma nova área mapeada e salva no repositorio
                        AreaMapeada novaAreaMapeada = new AreaMapeada();
                        novaAreaMapeada.setBairro(denunciaCadastroDto.localizacao().bairro());
                        return areaMapeadaRepository.save(novaAreaMapeada);
                    });

            //Inserindo a área mapeada à localização
            localizacaoVerificada.setAreaMapeada(areaMapeada);
        } else {
            throw new EntityNotFoundException("Bairro não encontrado.");
        }
        // Salva a localização encontrada / criada
        localizacaoRepository.save(localizacaoVerificada);
        denuncia.setLocalizacao(localizacaoVerificada);


        // Verifica o denunciante
        if (denunciaCadastroDto.idDenunciante() != null) {
            Usuario denunciante = usuarioRepository.findById(denunciaCadastroDto.idDenunciante())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Denunciante não encontrado"));
            denuncia.setDenunciante(denunciante);
        } else if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario denunciante = (Usuario) authentication.getPrincipal();
            denuncia.setDenunciante(denunciante);
        } else {
            throw new RuntimeException("Usuário não autenticado");
        }

        return new DenunciaExibicaoDto(denunciaRepository.save(denuncia));
    }









    public DenunciaExibicaoDto atualizar(DenunciaCadastroDto denunciaCadastroDto){
        Denuncia denuncia = new Denuncia();
        BeanUtils.copyProperties(denunciaCadastroDto, denuncia);

        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(denuncia.getId());
        if(denunciaOptional.isPresent()){
            Localizacao localizacao = new Localizacao();
            BeanUtils.copyProperties(denunciaCadastroDto.localizacao(), localizacao);

            Localizacao localizacaoVerificada = localizacaoRepository.findByCoordenadas(localizacao.getCoordenadas())
                    .orElseGet(() -> {
                        return localizacao;
                    });

            if (denunciaCadastroDto.localizacao().bairro() != null) {
                AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(denunciaCadastroDto.localizacao().bairro())
                        .orElseGet(() -> {
                            AreaMapeada novaAreaMapeada = new AreaMapeada();
                            novaAreaMapeada.setBairro(denunciaCadastroDto.localizacao().bairro());
                            return areaMapeadaRepository.save(novaAreaMapeada);
                        });
                localizacaoVerificada.setAreaMapeada(areaMapeada);
            } else {
                throw new EntityNotFoundException("Bairro não encontrado.");
            }
            localizacaoRepository.save(localizacaoVerificada);
            denuncia.setLocalizacao(localizacaoVerificada);

            if (denunciaCadastroDto.idDenunciante() != null) {
                Usuario denunciante = usuarioRepository.findById(denunciaCadastroDto.idDenunciante())
                        .orElseThrow(() -> new UsuarioNaoEncontradoException("Denunciante não encontrado"));
                denuncia.setDenunciante(denunciante);
            } else{
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Usuario denunciante = (Usuario) authentication.getPrincipal();
                denuncia.setDenunciante(denunciante);
            }
            return new DenunciaExibicaoDto(denunciaRepository.save(denuncia));
        }
        else {
            throw new DenunciaNaoEncontradaException("Denúncia não encontrada");        }
    }




    public DenunciaExibicaoDto buscarPorId(Long id){
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(id);
        if(denunciaOptional.isPresent()){
            System.out.println(denunciaOptional.get());
            return new DenunciaExibicaoDto(denunciaOptional.get());
        }
        else {
            throw new DenunciaNaoEncontradaException("Denúncia não encontrado");
        }
    }

    public Page<DenunciaExibicaoDto> listarTodasDenuncias(Pageable paginacao)
    {
        return denunciaRepository
                .findAll(paginacao)
                .map(DenunciaExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(id);
        if(denunciaOptional.isPresent()){
            denunciaRepository.delete(denunciaOptional.get());
        }
        else {
            throw new DenunciaNaoEncontradaException("Denúncia não encontrado");
        }
    }

}
