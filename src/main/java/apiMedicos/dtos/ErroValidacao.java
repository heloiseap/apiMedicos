package apiMedicos.dtos;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class ErroValidacao {
    private String campo;
    private String mensagem;

    public ErroValidacao(FieldError erro){
        this.campo = erro.getField();
        this.mensagem = erro.getDefaultMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacao>> trataParametroInvalido(MethodArgumentNotValidException exception){
        List<FieldError> erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(
                erros.stream().map(ErroValidacao::new).toList());
    }

}
