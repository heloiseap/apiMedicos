package apiMedicos.dtos;

import apiMedicos.enums.EspecialidadeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String crm;
    @NotBlank
    private String especialidade;
    @NotBlank
    private String dataNascimento;

}
