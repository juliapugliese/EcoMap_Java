package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.AreaMapeadaCadastroDto;
import br.com.fiap.ecoMap.dto.AreaMapeadaExibicaoDto;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.Drone;
import br.com.fiap.ecoMap.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AreaMapeadaService {
    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private DenunciaRepository denunciaRepository;

    public AreaMapeadaExibicaoDto gravar(AreaMapeadaCadastroDto areaCadastroDto) {
        AreaMapeada areaMapeada = new AreaMapeada();
        BeanUtils.copyProperties(areaCadastroDto, areaMapeada);

        if (areaCadastroDto.idColeta() != null) {
            Coleta coleta = coletaRepository.findById(areaCadastroDto.idColeta())
                    .orElseThrow(() -> new EntityNotFoundException("Coleta não encontrada"));
            areaMapeada.setColeta(coleta);

        }if (areaCadastroDto.idDrone() != null) {
            Drone drone = droneRepository.findById(areaCadastroDto.idDrone())
                    .orElseThrow(() -> new EntityNotFoundException("Drone não encontrada"));
            areaMapeada.setDrone(drone);
        }
        areaMapeada = areaMapeadaRepository.save(areaMapeada);




//        if (areaCadastroDto.idDenuncia() != null) {
//            Optional<Denuncia> denunciaOptional = denunciaRepository.findById(areaCadastroDto.idDenuncia());
//            if (denunciaOptional.isPresent()) {
//                Denuncia denuncia = denunciaOptional.get();
//
//                // Cria a nova AreaSolicitacao
//                AreaMapeada areaSolicitacao = new AreaMapeada();
//
//                // Salva a AreaSolicitacao no repositório
//                areaMapeadaRepository.save(areaSolicitacao);
//            } else {
//                throw new EntityNotFoundException("Denúncia não encontrada");
//            }
//        }

        return new AreaMapeadaExibicaoDto(areaMapeada);
    }


    public AreaMapeadaExibicaoDto atualizar(AreaMapeadaCadastroDto areaCadastroDto){
        AreaMapeada areaMapeada = new AreaMapeada();
        BeanUtils.copyProperties(areaCadastroDto, areaMapeada);

        Optional<AreaMapeada> areaOptional = areaMapeadaRepository.findById(areaMapeada.getId());
        if(areaOptional.isPresent()){
            if (areaCadastroDto.idColeta() != null) {
                Coleta coleta = coletaRepository.findById(areaCadastroDto.idColeta())
                        .orElseThrow(() -> new EntityNotFoundException("Coleta não encontrada"));
                areaMapeada.setColeta(coleta);

            } else if (areaCadastroDto.idDrone() != null) {
                Drone drone = droneRepository.findById(areaCadastroDto.idDrone())
                        .orElseThrow(() -> new EntityNotFoundException("Drone não encontrada"));
                areaMapeada.setDrone(drone);

            }
            return new AreaMapeadaExibicaoDto(areaMapeadaRepository.save(areaMapeada));
        }
        else {
            throw new EntityNotFoundException("Área não encontrada");        }
    }


    public AreaMapeadaExibicaoDto buscarPorId(Long id){
        Optional<AreaMapeada> areaOptional = areaMapeadaRepository.findById(id);
        if(areaOptional.isPresent()){
            return new AreaMapeadaExibicaoDto(areaOptional.get());
        }
        else {
            throw new EntityNotFoundException("Área não encontrada");
        }
    }

    public Page<AreaMapeadaExibicaoDto> listarTodasAreas(Pageable paginacao)
    {
        return areaMapeadaRepository
                .findAll(paginacao)
                .map(AreaMapeadaExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<AreaMapeada> areaOptional = areaMapeadaRepository.findById(id);
        if(areaOptional.isPresent()){
            areaMapeadaRepository.delete(areaOptional.get());
        }
        else {
            throw new EntityNotFoundException("Área não encontrada");
        }
    }


}
