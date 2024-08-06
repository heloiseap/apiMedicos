package apiMedicos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoShortResponse {
    private Long id;
    private String nome;
    private String especialidade;
    private LocalDate dataNascimento;
}
