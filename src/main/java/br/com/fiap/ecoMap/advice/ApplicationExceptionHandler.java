package br.com.fiap.ecoMap.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler
//onde centralizamos todos os tratamentos de erro da nossa aplicacao
{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro)
    //tratamento para validacao
    //Map<String, String> = Map<Chave, valor>, chave identifica o que é a string
    {
        Map<String, String> mapaDeErro = new HashMap<>();
        List<FieldError> camposErros = erro.getBindingResult().getFieldErrors();

        for(FieldError campo : camposErros){
            mapaDeErro.put(campo.getField(), campo.getDefaultMessage());
            //campo.getField() = pega o nome do erro / campo.getDefaultMessage() = pega a mensagem
        }
        return mapaDeErro;
        //classe FieldError do spring validation
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDosDados(){
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("erro", "Usuário já cadastrado");
        return mapaErro;
    }
}
