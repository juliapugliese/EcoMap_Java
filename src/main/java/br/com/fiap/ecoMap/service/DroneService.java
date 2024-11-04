package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.DroneCadastroDto;
import br.com.fiap.ecoMap.dto.DroneExibicaoDto;
import br.com.fiap.ecoMap.exception.UsuarioNaoEncontradoException;
import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Drone;
import br.com.fiap.ecoMap.repository.AreaMapeadaRepository;
import br.com.fiap.ecoMap.repository.DroneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private AreaMapeadaRepository areaMapeadaRepository;

    public DroneExibicaoDto gravar(DroneCadastroDto droneCadastroDto){
        List<Drone> dronesExistentes = droneRepository.findByIdAreas(droneCadastroDto.idAreas());
        Drone drone;
        if (!dronesExistentes.isEmpty() && dronesExistentes.getFirst().getModelo().equals(droneCadastroDto.modelo())) {
            drone = dronesExistentes.getFirst();
        } else {
            drone = new Drone();
            BeanUtils.copyProperties(droneCadastroDto, drone);
            drone = droneRepository.save(drone);
        }

        if (droneCadastroDto.idAreas() != null && !droneCadastroDto.idAreas().isEmpty()) {
            for (Long idArea : droneCadastroDto.idAreas()) {
                AreaMapeada areaMapeada = areaMapeadaRepository.findById(idArea)
                        .orElseThrow(() -> new EntityNotFoundException("Área não cadastrada no sistema"));
                areaMapeada.setDrone(drone);
                areaMapeadaRepository.save(areaMapeada);
            }
        }
        return new DroneExibicaoDto(drone);
    }

    public DroneExibicaoDto buscarPorId(Long id){
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if(droneOptional.isPresent()){
            System.out.println(droneOptional.get());
            return new DroneExibicaoDto(droneOptional.get());
        }
        else {
            throw new UsuarioNaoEncontradoException("contato não encontrado");
        }
    }

    public Page<DroneExibicaoDto> listarTodosDrones(Pageable paginacao)
    {
        return droneRepository
                .findAll(paginacao)
                .map(DroneExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if(droneOptional.isPresent()){
            droneRepository.delete(droneOptional.get());
        }
        else {
            throw new UsuarioNaoEncontradoException("contato não encontrado");
        }
    }


    public DroneExibicaoDto atualizar(DroneCadastroDto droneCadastroDto){
        Drone drone = new Drone();
        BeanUtils.copyProperties(droneCadastroDto, drone);
        Optional<Drone> droneOptional = droneRepository.findById(drone.getId());
        if(droneOptional.isPresent()){
            return new DroneExibicaoDto(droneRepository.save(drone));
        }
        else {
            throw new UsuarioNaoEncontradoException("contato não encontrado");
        }
    }

}
