package apiMedicos.repositories;

import apiMedicos.entities.MedicoEntity;
import apiMedicos.enums.EspecialidadeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoEntity,Long> {
    boolean existsByCrm(String crm);

    Optional<Object> findByCrm(String crm);
    Page<MedicoEntity> findAllByNome(String nome, Pageable pageable);
    Page<MedicoEntity> findAllByEspecialidade(EspecialidadeEnum especialidade, Pageable pageable);
    Page<MedicoEntity> findAllByDataNascimento(LocalDate dataNascimento, Pageable pageable);


}
