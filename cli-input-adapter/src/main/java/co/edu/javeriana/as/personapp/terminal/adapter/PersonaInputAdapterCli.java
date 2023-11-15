package co.edu.javeriana.as.personapp.terminal.adapter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.terminal.mapper.PersonaMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class PersonaInputAdapterCli {

    private static String id = "CLI[PERSONA]: ";
	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private PersonaMapperCli personaMapperCli;

	PersonInputPort personInputPort;

	public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}
    public void historial() {
        log.info(id + "Historial");
        try {
            personInputPort.findAll().stream()
                    .map(personaMapperCli::fromDomainToAdapterCli)
                    .forEach(person -> {
                        log.info(id + "Historial Persona: {}", person);
                        System.out.println(person);
                    });
        } catch (Exception e) {
            log.error(id + "Error al obtener historial de personas: {}", e.getMessage(), e);
            System.out.println("Error al obtener historial de personas.");
        }
    }

    public void crearPersona(PersonaModelCli personaModelCli) {
        try {
            log.info(id + "Creando Persona");
            Person person = personInputPort.create(personaMapperCli.fromAdapterToDomain(personaModelCli));
            System.out.println("Persona creada exitosamente");
            System.out.println(person);
        } catch (Exception e) {
            log.error(id + "Error al crear persona: {}", e.getMessage(), e);
            System.out.println("La persona no ha podido ser creada");
        }
    }

    public void obtenerPersona(Integer idPersona) {
        try {
            log.info(id + "Obteniendo Persona con id: {}", idPersona);
            System.out.println(personInputPort.findOne(idPersona));
        } catch (Exception e) {
            log.error(id + "Error al obtener persona: {}", e.getMessage(), e);
            System.out.println("La persona con id " + idPersona + " no existe en el sistema");
        }
    }

    public void editarPersona(PersonaModelCli personaModelCli) {
        try {
            log.info(id + "Editando Persona");
            Person person = personInputPort.edit(personaModelCli.getCc(),
                    personaMapperCli.fromAdapterToDomain(personaModelCli));
            System.out.println("Persona editada exitosamente");
            System.out.println(person);
        } catch (Exception e) {
            log.error(id + "Error al editar persona: {}", e.getMessage(), e);
            System.out.println("La persona no ha podido ser editada");
        }
    }

    public void eliminarPersona(Integer idPersona) {
        try {
            log.info(id + "Eliminando Persona con id: {}", idPersona);
            personInputPort.drop(idPersona);
            System.out.println("Persona con la cédula " + idPersona + " ha sido eliminada");
        } catch (Exception e) {
            log.error(id + "Error al eliminar persona: {}", e.getMessage(), e);
            System.out.println("La persona no ha podido ser eliminada");
        }
    }
}