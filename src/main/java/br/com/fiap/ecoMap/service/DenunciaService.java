package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.DenunciaCadastroDto;
import br.com.fiap.ecoMap.dto.DenunciaExibicaoDto;
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

        if (denunciaCadastroDto.localizacao() != null) {
            System.out.println("LocalizacaoCadastroDto: " + denunciaCadastroDto.localizacao());
            Localizacao localizacao = new Localizacao();
            BeanUtils.copyProperties(denunciaCadastroDto.localizacao(), localizacao);

            // Verifica se o bairro está preenchido
            if (denunciaCadastroDto.localizacao().bairro() != null) {
                // Tenta encontrar a área mapeada pelo bairro
                AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(denunciaCadastroDto.localizacao().bairro())
                        .orElseGet(() -> {
                            // Se não encontrar, cria uma nova área mapeada
                            AreaMapeada novaAreaMapeada = new AreaMapeada();
                            novaAreaMapeada.setBairro(denunciaCadastroDto.localizacao().bairro());
                            // Aqui você pode definir outros campos da nova área mapeada, se necessário
                            return areaMapeadaRepository.save(novaAreaMapeada);
                        });

                // Associa a área mapeada à localização
                localizacao.setAreaMapeada(areaMapeada);
                System.out.println("Área encontrada ou criada: " + areaMapeada.getBairro());
            } else {
                System.out.println("Bairro não preenchido no localizacao.");
            }

            // Salva a localização e associa à denúncia
            Localizacao savedArea = localizacaoRepository.save(localizacao);
            if (savedArea.getId() != null) {
                System.out.println("Localização salva com ID: " + savedArea.getId());
                denuncia.setLocalizacao(savedArea);
            } else {
                System.out.println("Falha ao salvar a localização.");
                throw new EntityNotFoundException("Localização não foi salva corretamente.");
            }
        } else {
            System.out.println("localizacao é nulo.");
            throw new EntityNotFoundException("Localização não fornecida.");
        }

        // Verifica se o id do denunciante está preenchido
        if (denunciaCadastroDto.idDenunciante() != null) {
            Usuario denunciante = usuarioRepository.findById(denunciaCadastroDto.idDenunciante())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Denunciante não encontrado"));
            denuncia.setDenunciante(denunciante);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario denunciante = (Usuario) authentication.getPrincipal();
            denuncia.setDenunciante(denunciante);
        }

        // Salva a denúncia e retorna o DTO
        return new DenunciaExibicaoDto(denunciaRepository.save(denuncia));
    }









    public DenunciaExibicaoDto atualizar(DenunciaCadastroDto denunciaCadastroDto){
        Denuncia denuncia = new Denuncia();
        BeanUtils.copyProperties(denunciaCadastroDto, denuncia);

        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(denuncia.getId());
        if(denunciaOptional.isPresent()){
            if (denunciaCadastroDto.idDenunciante() != null) {
                Usuario denunciante = usuarioRepository.findById(denunciaCadastroDto.idDenunciante())
                        .orElseThrow(() -> new UsuarioNaoEncontradoException("Denunciante não encontrado"));
                denuncia.setDenunciante(denunciante);
            }
            return new DenunciaExibicaoDto(denunciaRepository.save(denuncia));
        }
        else {
            throw new EntityNotFoundException("Denúncia não encontrada");        }
    }




    public DenunciaExibicaoDto buscarPorId(Long id){
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(id);
        if(denunciaOptional.isPresent()){
            return new DenunciaExibicaoDto(denunciaOptional.get());
        }
        else {
            throw new EntityNotFoundException("Denúncia não encontrado");
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
            throw new EntityNotFoundException("Denúncia não encontrado");
        }
    }

}
