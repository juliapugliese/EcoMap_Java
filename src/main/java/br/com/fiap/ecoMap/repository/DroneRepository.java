package br.com.fiap.ecoMap.repository;

import br.com.fiap.ecoMap.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
