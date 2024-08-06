package apiMedicos.repositories;

import apiMedicos.entities.MedicoEntity;
import apiMedicos.enums.EspecialidadeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoEntity,Long> {
    boolean existsByCrm(String crm);

    Optional<Object> findByCrm(String crm);
    List<MedicoEntity> findAllByNome(String nome);
    List<MedicoEntity> findAllByEspecialidade(EspecialidadeEnum especialidade);
    List<MedicoEntity> findAllByDataNascimento(LocalDate dataNascimento);


}
