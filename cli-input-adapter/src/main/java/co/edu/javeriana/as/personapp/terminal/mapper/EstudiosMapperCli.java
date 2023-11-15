package co.edu.javeriana.as.personapp.terminal.mapper;

import java.util.Objects;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.EstudiosModelCli;

@Mapper
public class EstudiosMapperCli {

    public EstudiosModelCli fromDomainToAdapterCli(Study study) {
        Objects.requireNonNull(study, "Study cannot be null");

        EstudiosModelCli estudiosModelCli = new EstudiosModelCli(
            study.getPerson().getIdentification(),
            study.getProfession().getIdentification(),
            study.getGraduationDate(),
            study.getUniversityName()
        );
        return estudiosModelCli;
    }

    public Study fromAdapterToDomain(EstudiosModelCli estudiosModelCli, Person person, Profession profession) {
        Objects.requireNonNull(estudiosModelCli, "EstudiosModelCli cannot be null");
        Objects.requireNonNull(person, "Person cannot be null");
        Objects.requireNonNull(profession, "Profession cannot be null");

        return new Study(person, profession, estudiosModelCli.getFechaGraduacion(), estudiosModelCli.getUniversidad());
    }
}
