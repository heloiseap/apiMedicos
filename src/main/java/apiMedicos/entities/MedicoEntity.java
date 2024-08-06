package apiMedicos.entities;

import apiMedicos.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String crm;

    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
}
