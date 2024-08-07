package apiMedicos.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum EspecialidadeEnum {
    IMUNULOGIA("Imunologia"),
    ANESTESIOLOGIA("Cardiologia"),
    ANGIOLOGIA("Angiologia"),
    CARDIOLOGIA("Cardiologia"),
    CIRURGIA_GERAL("Cirurgia geral"),
    DERMATOLOGIA("Dermatologia"),
    ENDOCRINOLOGIA("Endocrinologia"),
    GASTROENTEROLOGIA("Gastroenterologia"),
    GERIATRIA("Geriatria"),
    GINECOLOGIA("Ginecologia"),
    HEMATOLOGIA("Hematologia"),
    ESPORTIVA("Esportiva"),
    REABILITACAO("Reabilitação"),
    PERICIA("Perícia"),
    NEUROLOGIA("Neurologia"),
    OBSTETRICIA("Obstetricia"),
    OFTALMOLOGIA("Oftalmologia"),
    ORTOPEDIA("Ortopedia"),
    OTORRINOLARINGOLOGIA("Otorrinolaringologia"),
    PEDIATRA("Pediatra"),
    PSIQUIATRA("Psiquiatra"),
    UROLOGIA("Urologia");

    private final String valor;
    public static EspecialidadeEnum fromValue(String valor){
        for (EspecialidadeEnum especialidadeEnum: values()){
            if (especialidadeEnum.getValor().equals(valor)){
                return especialidadeEnum;
            }
        }
        throw new IllegalArgumentException(valor + ": Valor não encontrado");
    }

    public static List<EspecialidadeEnum> getAllEspecialidades(){
        return Arrays.asList(values());
    }
}
