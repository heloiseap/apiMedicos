package apiMedicos.controllers;

import apiMedicos.dtos.MedicoRequest;
import apiMedicos.dtos.MedicoResponse;
import apiMedicos.dtos.MedicoShortResponse;
import apiMedicos.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    //todo
    @PutMapping
    //@ResponseStatus(HttpStatus.FOUND)
    public Page<MedicoShortResponse> pesquisa(
            @Valid
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "especialidade", required = false) String especialidade,
            @RequestParam(value = "dataNascimento", required = false) String dataNascimento,
            Pageable pageable
    ) {
        return medicoService.pesquisar(nome, especialidade, dataNascimento,pageable);
    }

    @GetMapping()
    //@ResponseStatus(HttpStatus.OK)
    public Page<MedicoShortResponse> lista(Pageable pageable) {
        return medicoService.listar(pageable);
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastra(
            @Valid @RequestBody MedicoRequest medicoRequest){
        medicoService.cadastrar(medicoRequest);
    }

    @PutMapping("/{id}")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> atualiza(
            @PathVariable(name = "id") Long idMedico,
            @Valid @RequestBody MedicoRequest medicoRequest
    ){
        medicoService.atualizar(idMedico, medicoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    //ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> exclui(@Valid @PathVariable(name="id") Long idMedico
    ){
        medicoService.excluir(idMedico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.FOUND)
    public MedicoResponse detalhes(@Valid @PathVariable(name = "id") Long idMedico){
        return medicoService.detalhar(idMedico);
    }
}
