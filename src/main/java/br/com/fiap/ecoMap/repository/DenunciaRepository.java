package br.com.fiap.ecoMap.repository;

import br.com.fiap.ecoMap.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
}
