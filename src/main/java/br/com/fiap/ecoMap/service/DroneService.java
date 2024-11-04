package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.DroneCadastroDto;
import br.com.fiap.ecoMap.dto.DroneExibicaoDto;
import br.com.fiap.ecoMap.exception.UsuarioNaoEncontradoException;
import br.com.fiap.ecoMap.model.Drone;
import br.com.fiap.ecoMap.repository.DroneRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    public DroneExibicaoDto gravar(DroneCadastroDto droneCadastroDto){
        Drone drone = new Drone();
        BeanUtils.copyProperties(droneCadastroDto, drone);
        return new DroneExibicaoDto(droneRepository.save(drone));
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
