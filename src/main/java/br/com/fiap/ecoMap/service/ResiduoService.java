package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.ResiduoCadastroDto;
import br.com.fiap.ecoMap.dto.ResiduoExibicaoDto;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Residuo;
import br.com.fiap.ecoMap.repository.AreaMapeadaRepository;
import br.com.fiap.ecoMap.repository.ResiduoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResiduoService {
    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;


    public ResiduoExibicaoDto gravar(ResiduoCadastroDto residuoCadastroDto) {
        Residuo residuo = new Residuo();
        BeanUtils.copyProperties(residuoCadastroDto, residuo);

        if (residuoCadastroDto.bairro() != null) {
            AreaMapeada area = areaMapeadaRepository.findByBairro(residuoCadastroDto.bairro())
                    .orElseThrow(() -> new EntityNotFoundException("Área não encontrada"));
            residuo.setAreaMapeada(area);
        }
        return new ResiduoExibicaoDto(residuoRepository.save(residuo));
    }


    public ResiduoExibicaoDto buscarPorId(Long id){
        Optional<Residuo> residuoOptional = residuoRepository.findById(id);
        if(residuoOptional.isPresent()){
            return new ResiduoExibicaoDto(residuoOptional.get());
        }
        else {
            throw new EntityNotFoundException("Resíduo não encontrado");
        }
    }

    public Page<ResiduoExibicaoDto> listarTodosResiduos(Pageable paginacao)
    {
        return residuoRepository
                .findAll(paginacao)
                .map(ResiduoExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Residuo> residuoOptional = residuoRepository.findById(id);
        if(residuoOptional.isPresent()){
            residuoRepository.delete(residuoOptional.get());
        }
        else {
            throw new EntityNotFoundException("Resíduo não encontrado");
        }
    }

    public ResiduoExibicaoDto atualizar(ResiduoCadastroDto residuoCadastroDto){
        Residuo residuo = new Residuo();
        BeanUtils.copyProperties(residuoCadastroDto, residuo);

        Optional<Residuo> residuoOptional = residuoRepository.findById(residuo.getId());
        if(residuoOptional.isPresent()){
            if (residuoCadastroDto.bairro() != null) {
                AreaMapeada area = areaMapeadaRepository.findByBairro(residuoCadastroDto.bairro())
                        .orElseThrow(() -> new EntityNotFoundException("Área não encontrada"));
                residuo.setAreaMapeada(area);
            }
            return new ResiduoExibicaoDto(residuoRepository.save(residuo));
        }
        else {
            throw new EntityNotFoundException("Resíduo não encontrado");        }
    }

}
