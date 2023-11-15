package co.edu.javeriana.as.personapp.terminal.mapper;

import java.util.Collections;
import java.util.Objects;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;

@Mapper
public class ProfesionMapperCli {

    public ProfesionModelCli fromDomainToAdapterCli(Profession profession) {
        Objects.requireNonNull(profession, "Profession cannot be null");

        return new ProfesionModelCli(
            profession.getIdentification(),
            profession.getName(),
            profession.getDescription()
        );
    }

    public Profession fromAdapterToDomain(ProfesionModelCli profesionModelCli) {
        Objects.requireNonNull(profesionModelCli, "ProfesionModelCli cannot be null");

        return new Profession(
            profesionModelCli.getIdentificacion(),
            profesionModelCli.getNombre(),
            profesionModelCli.getDescripcion(),
            Collections.emptyList()
        );
    }
}
