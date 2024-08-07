package apiMedicos.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErroResponse {
    private String campo;
    private String mensagem;

    public ErroResponse(FieldError fieldError){
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }





}
