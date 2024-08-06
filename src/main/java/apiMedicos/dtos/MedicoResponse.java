package apiMedicos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicoResponse {
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    private LocalDate dataNascimento;
}
