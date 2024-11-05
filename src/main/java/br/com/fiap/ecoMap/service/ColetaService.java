package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.ColetaCadastroDto;
import br.com.fiap.ecoMap.dto.ColetaExibicaoDto;
import br.com.fiap.ecoMap.exception.AreaNaoEncontradaException;
import br.com.fiap.ecoMap.exception.ColetaNaoEncontradaException;
import br.com.fiap.ecoMap.exception.UsuarioNaoEncontradoException;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Residuo;
import br.com.fiap.ecoMap.repository.AreaMapeadaRepository;
import br.com.fiap.ecoMap.repository.ColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    public ColetaExibicaoDto gravar(ColetaCadastroDto coletaCadastroDto) {
        List<Coleta> coletasExistentes = coletaRepository.findByIdAreas(coletaCadastroDto.idAreas());
        Coleta coleta;
        if (!coletasExistentes.isEmpty() && coletasExistentes.getFirst().getDataColeta().equals(coletaCadastroDto.dataColeta())) {
            coleta = coletasExistentes.getFirst();
        } else {
            coleta = new Coleta();
            BeanUtils.copyProperties(coletaCadastroDto, coleta);
            coleta = coletaRepository.save(coleta);
        }


        if (coletaCadastroDto.idAreas() != null && !coletaCadastroDto.idAreas().isEmpty()) {
            for (Long idArea : coletaCadastroDto.idAreas()) {
                AreaMapeada areaMapeada = areaMapeadaRepository.findById(idArea)
                        .orElseThrow(() -> new AreaNaoEncontradaException("Área não cadastrada no sistema"));
                long coletaQtResiduo = areaMapeada.getResiduos().stream().mapToLong(Residuo::getQuantidade).sum();

                coleta.setQuantidadeResiduo(coletaQtResiduo);
                coletaRepository.save(coleta);

                areaMapeada.setColeta(coleta);
                areaMapeadaRepository.save(areaMapeada);
            }
        }
        coletaRepository.save(coleta);
        return new ColetaExibicaoDto(coleta);
    }

    public ColetaExibicaoDto buscarPorId(Long id){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(id);

        if (coletaOptional.isPresent()){
            System.out.println(coletaOptional.get());
            return new ColetaExibicaoDto(coletaOptional.get());
        } else {
            throw new ColetaNaoEncontradaException("Coleta não existe no banco de dados!");
        }
    }

    public Page<ColetaExibicaoDto> listarTodasColetas(Pageable paginacao)
    {
        return coletaRepository
                .findAll(paginacao)
                .map(ColetaExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);
        if(coletaOptional.isPresent()){
            coletaRepository.delete(coletaOptional.get());
        }
        else {
            throw new ColetaNaoEncontradaException("Coleta não encontrado");
        }
    }

    public ColetaExibicaoDto atualizar(ColetaCadastroDto coletaCadastroDto) {
        Coleta coleta = coletaRepository.findById(coletaCadastroDto.id())
                .orElseThrow(() -> new ColetaNaoEncontradaException("Coleta não encontrada"));

        BeanUtils.copyProperties(coletaCadastroDto, coleta, "id");  // Exclude "id" from being overwritten

        if (coletaCadastroDto.idAreas() != null && !coletaCadastroDto.idAreas().isEmpty()) {
            for (Long idArea : coletaCadastroDto.idAreas()) {
                AreaMapeada areaMapeada = areaMapeadaRepository.findById(idArea)
                        .orElseThrow(() -> new AreaNaoEncontradaException("Área não cadastrada no sistema"));
                long coletaQtResiduo = areaMapeada.getResiduos().stream().mapToLong(Residuo::getQuantidade).sum();

                coleta.setQuantidadeResiduo(coletaQtResiduo);
                coletaRepository.save(coleta);

                areaMapeada.setColeta(coleta);
                areaMapeadaRepository.save(areaMapeada);
            }
        }
        coleta = coletaRepository.save(coleta);

        return new ColetaExibicaoDto(coleta);
    }


    public void atualizarQtResiduo(ColetaCadastroDto coletaDTO){

        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaDTO, coleta);

        if (coleta.getAreas() != null && !coleta.getAreas().isEmpty()) {
            coleta.setQuantidadeResiduo(
                    coleta.getAreas()
                            .stream()
                            .filter(areaMapeada -> areaMapeada.getResiduos() != null)
                            .mapToLong(areaMapeada -> areaMapeada
                                    .getResiduos()
                                    .stream()
                                    .filter(Objects::nonNull)
                                    .mapToLong(Residuo::getQuantidade)
                                    .sum()
                            ).sum()


            );
        } else {
            coleta.setQuantidadeResiduo(null);
        }

        coletaRepository.save(coleta);
    }


}
