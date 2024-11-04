package br.com.fiap.ecoMap.repository;

import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    Optional<AreaMapeada> findByCoordenadas(String coordenadas);

}
