package apiMedicos.exceptions;

import apiMedicos.exceptions.dtos.ErroResponse;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    private static final Logger LOGGER = LogManager.getLogger(TratadorDeErros.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> entidadeNaoEncontrada() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErroResponse> trataChaveDuplicada(DuplicateKeyException exception){
        ErroResponse erro = new ErroResponse();
        erro.setCampo("crm");
        erro.setMensagem(exception.getMessage());

        LOGGER.error("CRM já cadastrado.", exception);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResponse>> trataErroValidacao(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErros = exception.getFieldErrors();
        List<ErroResponse> responseList = fieldErros.stream().map(ErroResponse::new).toList();

        responseList.forEach(
                errorResponse -> LOGGER.warn("Campo obrigatório: {}", errorResponse.getCampo()));

        return ResponseEntity.badRequest().body(responseList);
    }*/

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> trataCampoVazio(IllegalArgumentException exception){
        ErroResponse erro = new ErroResponse();

        erro.setCampo("nome");
        erro.setMensagem("Campo nome deve ser preenchido");

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(InvalidPropertiesFormatException.class)
    public ResponseEntity<ErroResponse> trataCrmInvalido(InvalidPropertiesFormatException exception){
        ErroResponse response = new ErroResponse();
        response.setCampo("crm");
        response.setMensagem(exception.getMessage());

        return ResponseEntity.badRequest().body(response);
    }


}
