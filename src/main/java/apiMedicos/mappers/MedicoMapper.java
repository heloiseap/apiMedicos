package apiMedicos.mappers;

import apiMedicos.dtos.MedicoRequest;
import apiMedicos.dtos.MedicoResponse;
import apiMedicos.dtos.MedicoShortResponse;
import apiMedicos.entities.MedicoEntity;
import apiMedicos.enums.EspecialidadeEnum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MedicoMapper {

    public static MedicoEntity map(MedicoRequest source) {
        if (source == null) return null;

        String dataString = source.getDataNascimento();

        MedicoEntity target = new MedicoEntity();
        target.setCrm(source.getCrm());
        target.setNome(source.getNome());
        target.setEspecialidade(EspecialidadeEnum.fromValue(source.getEspecialidade()));
        target.setDataNascimento(LocalDate.parse(dataString));

        return target;
    }

    public static MedicoResponse map(MedicoEntity source) {
        if (source == null) return null;

        MedicoResponse target = new MedicoResponse();
        target.setId(source.getId());
        target.setCrm(source.getCrm());
        target.setNome(source.getNome());
        target.setEspecialidade(String.valueOf(EspecialidadeEnum.valueOf(String.valueOf(source.getEspecialidade()))));
        target.setDataNascimento(source.getDataNascimento());

        return target;
    }
    public static MedicoShortResponse mapShort(MedicoEntity source) {
        if (source == null) return null;

        MedicoShortResponse target = new MedicoShortResponse();
        target.setId(source.getId());
        target.setNome(source.getNome());
        target.setEspecialidade(String.valueOf(EspecialidadeEnum.valueOf(String.valueOf(source.getEspecialidade()))));
        target.setDataNascimento(source.getDataNascimento());

        return target;
    }


}
