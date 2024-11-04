package br.com.fiap.ecoMap.repository;

import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("SELECT c FROM Drone c JOIN c.areas a WHERE a.id IN :idAreas")
    List<Drone> findByIdAreas(@Param("idAreas") List<Long> idAreas);
}
