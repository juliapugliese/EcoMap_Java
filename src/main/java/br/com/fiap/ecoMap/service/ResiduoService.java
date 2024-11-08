package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.ResiduoCadastroDto;
import br.com.fiap.ecoMap.dto.ResiduoExibicaoDto;
import br.com.fiap.ecoMap.exception.ResiduoNaoEncontradoException;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Residuo;
import br.com.fiap.ecoMap.repository.AreaMapeadaRepository;
import br.com.fiap.ecoMap.repository.ColetaRepository;
import br.com.fiap.ecoMap.repository.ResiduoRepository;
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

    @Autowired
    private ColetaRepository coletaRepository;

    public ResiduoExibicaoDto gravar(ResiduoCadastroDto residuoCadastroDto) {
        Residuo residuo = new Residuo();
        BeanUtils.copyProperties(residuoCadastroDto, residuo);


        AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(residuoCadastroDto.bairro())
                .orElseGet(() -> {
                    // Se não encontrar, cria uma nova área mapeada e salva no repositorio
                    AreaMapeada novaAreaMapeada = new AreaMapeada();
                    novaAreaMapeada.setBairro(residuoCadastroDto.bairro());
                    return areaMapeadaRepository.save(novaAreaMapeada);
                });

        Coleta coleta = areaMapeada.getColeta();
        if (coleta != null) {
            long coletaQtResiduo = coleta.getQuantidadeResiduo() != null ? coleta.getQuantidadeResiduo() : 0;
            coleta.setQuantidadeResiduo(coletaQtResiduo += residuo.getQuantidade());
            coletaRepository.save(coleta);
        }
        residuo.setAreaMapeada(areaMapeada);

        return new ResiduoExibicaoDto(residuoRepository.save(residuo));
    }


    public ResiduoExibicaoDto buscarPorId(Long id){
        Optional<Residuo> residuoOptional = residuoRepository.findById(id);
        if(residuoOptional.isPresent()){
            System.out.println(residuoOptional.get());
            return new ResiduoExibicaoDto(residuoOptional.get());
        }
        else {
            throw new ResiduoNaoEncontradoException("Resíduo não encontrado");
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
            Coleta coleta = residuoOptional.get().getAreaMapeada().getColeta();
            if (coleta != null) {
                long coletaQtResiduo = coleta.getQuantidadeResiduo() != null ? coleta.getQuantidadeResiduo() : 0;
                coleta.setQuantidadeResiduo(coletaQtResiduo -= residuoOptional.get().getQuantidade());
                coletaRepository.save(coleta);
            }

            residuoRepository.delete(residuoOptional.get());
        }
        else {
            throw new ResiduoNaoEncontradoException("Resíduo não encontrado");
        }
    }

    public ResiduoExibicaoDto atualizar(ResiduoCadastroDto residuoCadastroDto){
        Residuo residuo = new Residuo();
        BeanUtils.copyProperties(residuoCadastroDto, residuo);

        Optional<Residuo> residuoOptional = residuoRepository.findById(residuo.getId());
        if(residuoOptional.isPresent()){
            AreaMapeada areaMapeada = areaMapeadaRepository.findByBairro(residuoCadastroDto.bairro())
                    .orElseGet(() -> {
                        // Se não encontrar, cria uma nova área mapeada e salva no repositorio
                        AreaMapeada novaAreaMapeada = new AreaMapeada();
                        novaAreaMapeada.setBairro(residuoCadastroDto.bairro());
                        return areaMapeadaRepository.save(novaAreaMapeada);
                    });
            Coleta coleta = areaMapeada.getColeta();
            if (coleta != null) {
                long coletaQtResiduo = coleta.getQuantidadeResiduo() != null ? coleta.getQuantidadeResiduo() : 0;
                coleta.setQuantidadeResiduo(coletaQtResiduo += residuo.getQuantidade());
                coletaRepository.save(coleta);
            }
            residuo.setAreaMapeada(areaMapeada);
            return new ResiduoExibicaoDto(residuoRepository.save(residuo));
        }
        else {
            throw new ResiduoNaoEncontradoException("Resíduo não encontrado");        }
    }

}
