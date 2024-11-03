package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.DenunciaCadastroDto;
import br.com.fiap.ecoMap.dto.DenunciaExibicaoDto;
import br.com.fiap.ecoMap.exception.UsuarioNaoEncontradoException;
import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.repository.DenunciaRepository;
import br.com.fiap.ecoMap.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public DenunciaExibicaoDto gravar(DenunciaCadastroDto denunciaCadastroDto) {
        Denuncia denuncia = new Denuncia();
        BeanUtils.copyProperties(denunciaCadastroDto, denuncia);

        if (denunciaCadastroDto.idDenunciante() != null) {
            Usuario denunciante = usuarioRepository.findById(denunciaCadastroDto.idDenunciante())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Denunciante não encontrado"));
            denuncia.setDenunciante(denunciante);
        } else {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            Usuario denunciante = (Usuario) usuarioRepository.findByEmail(authentication.getName());
//            denuncia.setDenunciante(denunciante);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario denunciante = (Usuario) authentication.getPrincipal();
            denuncia.setDenunciante(denunciante);
        }
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
