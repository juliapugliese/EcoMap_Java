package br.com.fiap.ecoMap.repository;


import br.com.fiap.ecoMap.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
    Optional<Coleta> findByDataColeta(LocalDate dataColeta);
    @Query("SELECT c FROM Coleta c JOIN c.areas a WHERE a.id IN :idAreas")
    List<Coleta> findByIdAreas(@Param("idAreas") List<Long> idAreas);

}
