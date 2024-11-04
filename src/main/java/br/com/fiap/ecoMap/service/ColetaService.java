package br.com.fiap.ecoMap.service;

import br.com.fiap.ecoMap.dto.ColetaCadastroDto;
import br.com.fiap.ecoMap.dto.ColetaExibicaoDto;
import br.com.fiap.ecoMap.exception.UsuarioNaoEncontradoException;
import br.com.fiap.ecoMap.model.Coleta;
import br.com.fiap.ecoMap.model.Residuo;
import br.com.fiap.ecoMap.repository.ColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
public class ColetaService {


    @Autowired
    private ColetaRepository coletaRepository;

    public ColetaExibicaoDto gravar(ColetaCadastroDto coletaCadastroDto){
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaCadastroDto, coleta);
        return new ColetaExibicaoDto(coletaRepository.save(coleta));
    }

    public ColetaExibicaoDto buscarPorId(Long id){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(id);

        if (coletaOptional.isPresent()){
            System.out.println(coletaOptional.get());
            return new ColetaExibicaoDto(coletaOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Coleta não existe no banco de dados!");
        }
    }

    public Page<ColetaExibicaoDto> listarTodasColetas(Pageable paginacao)
    {
        return coletaRepository
                .findAll(paginacao)
                .map(ColetaExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);
        if(coletaOptional.isPresent()){
            coletaRepository.delete(coletaOptional.get());
        }
        else {
            throw new UsuarioNaoEncontradoException("contato não encontrado");
        }
    }

    public ColetaExibicaoDto atualizar(ColetaCadastroDto coletaCadastroDto){
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaCadastroDto, coleta);
        Optional<Coleta> coletaOptional = coletaRepository.findById(coleta.getId());
        if(coletaOptional.isPresent()){
            return new ColetaExibicaoDto(coletaRepository.save(coleta));
        }
        else {
            throw new UsuarioNaoEncontradoException("Coleta não encontrado");
        }
    }


    public void atualizarQtResiduo(ColetaCadastroDto coletaDTO){

        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaDTO, coleta);

        if (coleta.getAreas() != null && !coleta.getAreas().isEmpty()) {
            coleta.setQuantidadeResiduo(
                    coleta.getAreas()
                            .stream()
                            .filter(areaMapeada -> areaMapeada.getResiduos() != null)
                            .mapToLong(areaMapeada -> areaMapeada
                                    .getResiduos()
                                    .stream()
                                    .filter(Objects::nonNull)
                                    .mapToLong(Residuo::getQuantidade)
                                    .sum()
                            ).sum()


            );
        } else {
            coleta.setQuantidadeResiduo(null);
        }

        coletaRepository.save(coleta);
    }

//        @Transactional
//        public void updateQuantidadeResiduo(Long coletaId) {
//        Coleta coleta = buscarPorId(coletaId);
//        coleta.updateQuantidadeResiduo();
//    }

//    public ColetaExibicaoDto atualizarQtResiduo(ColetaCadastroDto coletaDTO){
//
//        Coleta coleta = new Coleta();
//        BeanUtils.copyProperties(coletaDTO, coleta);
//
//        if (coleta.getAreas() != null && !coleta.getAreas().isEmpty()) {
//            coleta.setQuantidadeResiduo(
//                    coleta.getAreas()
//                            .stream()
//                            .filter(areaMapeada -> areaMapeada.getResiduos() != null)
//                            .mapToLong(areaMapeada -> areaMapeada
//                                    .getResiduos()
//                                    .stream()
//                                    .filter(Objects::nonNull)
//                                    .mapToLong(Residuo::getQuantidade)
//                                    .sum()
//                            ).sum()
//
//
//            );
//        } else {
//            coleta.setQuantidadeResiduo(null);
//        }
//
//        Coleta coletaSalva = coletaRepository.save(coleta);
//        return new ColetaExibicaoDto(coletaSalva);
//
//    }

}
