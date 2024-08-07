package apiMedicos.entities;

import apiMedicos.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MedicoEntity {
    public static final String CRM_FORMAT = "CRM/[A-Za-z][A-Za-z]\\s?\\d\\d\\d\\d\\d\\d";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp="CRM/[A-Za-z][A-Za-z]\\s?\\d\\d\\d\\d\\d\\d")
    private String crm;

    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
}
