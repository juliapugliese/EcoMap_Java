package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.AreaMapeadaCadastroDto;
import br.com.fiap.ecoMap.dto.AreaMapeadaExibicaoDto;
import br.com.fiap.ecoMap.exception.AreaNaoEncontradaException;
import br.com.fiap.ecoMap.exception.ColetaNaoEncontradaException;
import br.com.fiap.ecoMap.exception.DroneNaoEncontradoException;
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
        Optional<AreaMapeada> existingAreaOptional = areaMapeadaRepository.findByBairro(areaCadastroDto.bairro());

        AreaMapeada areaMapeada;
        if (existingAreaOptional.isPresent()) {
            areaMapeada = existingAreaOptional.get();
        } else {
            areaMapeada = new AreaMapeada();
            BeanUtils.copyProperties(areaCadastroDto, areaMapeada);
        }

        if (areaCadastroDto.idColeta() != null) {
            Coleta coleta = coletaRepository.findById(areaCadastroDto.idColeta())
                    .orElseThrow(() -> new ColetaNaoEncontradaException("Coleta não encontrada"));
            areaMapeada.setColeta(coleta);
        }
        if (areaCadastroDto.idDrone() != null) {
            Drone drone = droneRepository.findById(areaCadastroDto.idDrone())
                    .orElseThrow(() -> new DroneNaoEncontradoException("Drone não encontrado"));
            areaMapeada.setDrone(drone);
        }

        return new AreaMapeadaExibicaoDto(areaMapeadaRepository.save(areaMapeada));
    }


    public AreaMapeadaExibicaoDto atualizar(AreaMapeadaCadastroDto areaCadastroDto) {
        AreaMapeada areaMapeada = areaMapeadaRepository.findById(areaCadastroDto.id())
                .orElseThrow(() -> new AreaNaoEncontradaException("Área não encontrada"));

        if (areaCadastroDto.idColeta() != null) {
            Coleta coleta = coletaRepository.findById(areaCadastroDto.idColeta())
                    .orElseThrow(() -> new ColetaNaoEncontradaException("Coleta não encontrada"));
            areaMapeada.setColeta(coleta);
        }

        if (areaCadastroDto.idDrone() != null) {
            Drone drone = droneRepository.findById(areaCadastroDto.idDrone())
                    .orElseThrow(() -> new DroneNaoEncontradoException("Drone não encontrado"));
            areaMapeada.setDrone(drone);
        }

        BeanUtils.copyProperties(areaCadastroDto, areaMapeada, "id"); // Don't overwrite the ID

        return new AreaMapeadaExibicaoDto(areaMapeadaRepository.save(areaMapeada));
    }



    public AreaMapeadaExibicaoDto buscarPorId(Long id){
        Optional<AreaMapeada> areaOptional = areaMapeadaRepository.findById(id);
        if(areaOptional.isPresent()){
            System.out.println(areaOptional.get());
            return new AreaMapeadaExibicaoDto(areaOptional.get());
        }
        else {
            throw new AreaNaoEncontradaException("Área não encontrada");
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
            throw new AreaNaoEncontradaException("Área não encontrada");
        }
    }


}
