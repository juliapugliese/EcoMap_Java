package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.ResiduoCadastroDto;
import br.com.fiap.ecoMap.dto.ResiduoExibicaoDto;
import br.com.fiap.ecoMap.model.Residuo;
import br.com.fiap.ecoMap.repository.ResiduoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResiduoService {
    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private ColetaService coletaService;

    public ResiduoExibicaoDto gravar(ResiduoCadastroDto residuoCadastroDto){
        Residuo contato = new Residuo();
        BeanUtils.copyProperties(residuoCadastroDto, contato);
        return new ResiduoExibicaoDto(residuoRepository.save(contato));
    }
}
