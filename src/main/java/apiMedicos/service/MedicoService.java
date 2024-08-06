package apiMedicos.service;

import apiMedicos.dtos.MedicoRequest;
import apiMedicos.dtos.MedicoResponse;
import apiMedicos.dtos.MedicoShortResponse;
import apiMedicos.entities.MedicoEntity;
import apiMedicos.enums.EspecialidadeEnum;
import apiMedicos.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static apiMedicos.mappers.MedicoMapper.map;
import static apiMedicos.mappers.MedicoMapper.mapShort;

@RequiredArgsConstructor
@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public Page<MedicoShortResponse> listar(Pageable pageable) {
        return medicoRepository.findAll(pageable).map(
                medico -> new MedicoShortResponse(
                        medico.getId(),
                        medico.getNome(),
                        medico.getEspecialidade().toString(),
                        medico.getDataNascimento()
                )
        );
    }

    public MedicoResponse detalhar(Long idMedico) {
        MedicoEntity medico = medicoRepository.findById(Long.valueOf(idMedico)).orElse(null);
        return map(medico);
    }

    public void cadastrar(MedicoRequest medicoRequest) {
        if (medicoRepository.findByCrm(medicoRequest.getCrm()).isPresent()) {
            throw new DuplicateKeyException("Crm já cadastrado");
        }
        MedicoEntity medicoEntity = map(medicoRequest);
        medicoRepository.save(medicoEntity);
    }

    public void atualizar(Long idMedico, @Valid MedicoRequest medicoRequest) {
        if (!medicoRepository.existsById(idMedico)) {
            throw new EntityNotFoundException();
        }
        medicoRepository.save(map(medicoRequest));
    }

    public void excluir(Long idMedico) {
        if (medicoRepository.existsById(idMedico)) {
            medicoRepository.deleteById(idMedico);
        } else {
            throw new EntityNotFoundException();
        }

    }

    public Set<MedicoShortResponse> pesquisar(String nome, String especialidade, String dataNascimento) {

        Set<MedicoEntity> response = new HashSet<>();
        if (nome != null) {
            response.addAll(medicoRepository.findAllByNome(nome).stream().toList());
        }
        if (especialidade != null) {
            response.addAll(medicoRepository.findAllByEspecialidade(EspecialidadeEnum.valueOf(especialidade)).stream().toList());
        }
        if (dataNascimento != null)
            response.addAll(medicoRepository.findAllByDataNascimento(LocalDate.parse(dataNascimento)).stream().toList());

        return response.stream().map(
                medico -> mapShort(medico)
        ).collect(Collectors.toSet());
    }
}
