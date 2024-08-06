package apiMedicos.dtos;

import apiMedicos.enums.EspecialidadeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicoRequest {
    private String nome;
    private String crm;
    private String especialidade;
    private LocalDate dataNascimento;

}
