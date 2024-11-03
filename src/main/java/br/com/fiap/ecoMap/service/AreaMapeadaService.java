package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.AreaMapeadaCadastroDto;
import br.com.fiap.ecoMap.dto.AreaMapeadaExibicaoDto;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Drone;
import br.com.fiap.ecoMap.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaMapeadaService {
    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    public AreaMapeadaExibicaoDto gravar(AreaMapeadaCadastroDto areaCadastroDto) {
        AreaMapeada areaMapeada = new AreaMapeada();
        BeanUtils.copyProperties(areaCadastroDto, areaMapeada);

        if (areaCadastroDto.idColeta() != null) {
            Coleta coleta = coletaRepository.findById(areaCadastroDto.idColeta())
                    .orElseThrow(() -> new EntityNotFoundException("Coleta não encontrada"));
            areaMapeada.setColeta(coleta);

        } else if (areaCadastroDto.idDrone() != null) {
            Drone drone = droneRepository.findById(areaCadastroDto.idDrone())
                    .orElseThrow(() -> new EntityNotFoundException("Coleta não encontrada"));
            areaMapeada.setDrone(drone);

        }
        return new AreaMapeadaExibicaoDto(areaMapeadaRepository.save(areaMapeada));
    }
}
