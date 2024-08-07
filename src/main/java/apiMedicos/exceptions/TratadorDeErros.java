package apiMedicos.exceptions;

import apiMedicos.dtos.ErroValidacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> entidadeNaoEncontrada() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> exValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((erro) -> {
            String fieldName = ((FieldError) erro).getField();
            String errorMessage = erro.getDefaultMessage();
            erros.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }


/*    public static class InvalidCrmFormatException extends RuntimeException {
        public InvalidCrmFormatException(String message) {
            super(message);
        }
    }*/
/*
    @ExceptionHandler(InvalidCrmFormatException.class)
    public ResponseEntity<String> invalidCrmFormatException(InvalidCrmFormatException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }*/

}
