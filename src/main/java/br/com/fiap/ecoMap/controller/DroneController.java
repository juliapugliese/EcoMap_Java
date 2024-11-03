package br.com.fiap.ecoMap.controller;


import br.com.fiap.ecoMap.dto.DroneCadastroDto;
import br.com.fiap.ecoMap.dto.DroneExibicaoDto;
import br.com.fiap.ecoMap.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping("/drones")
    @ResponseStatus(HttpStatus.CREATED)
    public DroneExibicaoDto gravar(@RequestBody @Valid DroneCadastroDto droneCadastroDto)
    {
        return droneService.gravar(droneCadastroDto);
    }

    @GetMapping("/drones")
    @ResponseStatus(HttpStatus.OK)
    public Page<DroneExibicaoDto> listarTodosDrones(Pageable paginacao){
        return droneService.listarTodosDrones(paginacao);
    }

    @DeleteMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        droneService.excluir(id);
    }

    @PutMapping("/drones")
    @ResponseStatus(HttpStatus.OK)
    public DroneExibicaoDto atualizar(@RequestBody DroneCadastroDto droneCadastroDto){
        return droneService.atualizar(droneCadastroDto);
    }

    @GetMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneExibicaoDto buscarPorId(@PathVariable Long id) {
        return droneService.buscarPorId(id);
    }
}
