package apiMedicos.service;

import apiMedicos.dtos.MedicoRequest;
import apiMedicos.dtos.MedicoResponse;
import apiMedicos.dtos.MedicoShortResponse;
import apiMedicos.entities.MedicoEntity;
import apiMedicos.enums.EspecialidadeEnum;
import apiMedicos.exceptions.TratadorDeErros;
import apiMedicos.mappers.MedicoMapper;
import apiMedicos.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static apiMedicos.mappers.MedicoMapper.map;

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
        return map(medicoRepository.findById(idMedico).orElseThrow(EntityNotFoundException::new));
    }


    public void cadastrar(MedicoRequest medicoRequest) {
        if (medicoRepository.findByCrm(medicoRequest.getCrm()).isPresent()) {
            throw new DuplicateKeyException("Crm já cadastrado");
        }
        if (medicoRequest.getNome() == null) {
            throw new IllegalArgumentException("Campo nome obrigatório");
        }
        if (!medicoRequest.getCrm().matches(MedicoEntity.CRM_FORMAT)) {
            throw new IllegalArgumentException("CRM deve estar no formato 'CRM/UF XXXXXX' ou 'CRM/UFXXXXXX'");
        }

        MedicoEntity medicoEntity = map(medicoRequest);
        medicoRepository.save(medicoEntity);
        ResponseEntity.created(URI.create("")).build();
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

    //testar
    public Page<MedicoShortResponse> pesquisar(String nome, String especialidade, String dataNascimento, Pageable pageable) {

        Set<MedicoEntity> response = new HashSet<>();

        if (nome != null) {
            response.addAll(medicoRepository.findAllByNome(nome, pageable).stream().toList());
        }
        if (especialidade != null) {
            response.addAll(medicoRepository.findAllByEspecialidade(EspecialidadeEnum.valueOf(especialidade), pageable).stream().toList());
        }
        if (dataNascimento != null)
            response.addAll(medicoRepository.findAllByDataNascimento(LocalDate.parse(dataNascimento), pageable).stream().toList());

        List<MedicoShortResponse> responseList = response.stream().map(
                MedicoMapper::mapShort
        ).collect(Collectors.toList());

        return new PageImpl<>(responseList);
    }
}
