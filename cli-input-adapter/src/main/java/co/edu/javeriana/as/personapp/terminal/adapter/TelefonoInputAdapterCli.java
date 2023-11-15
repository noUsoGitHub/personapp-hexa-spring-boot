package co.edu.javeriana.as.personapp.terminal.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.TelefonoMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class TelefonoInputAdapterCli {

    private static String id = "CLI[TELEFONO]: ";
	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("phoneOutputAdapterMaria")
	private PhoneOutputPort phoneOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	@Qualifier("phoneOutputAdapterMongo")
	private PhoneOutputPort phoneOutputPortMongo;

	@Autowired
	private TelefonoMapperCli telefonoMapperCli;

	PersonInputPort personInputPort;

	PhoneInputPort phoneInputPort;

	public void setPhoneOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
			phoneInputPort = new PhoneUseCase(phoneOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
			phoneInputPort = new PhoneUseCase(phoneOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

    public void historial() {
        try {
            phoneInputPort.findAll().stream()
                    .map(telefonoMapperCli::fromDomainToAdapterCli)
                    .forEach(phone -> {
                        System.out.println(phone);
                    });
        } catch (Exception e) {
            System.out.println("Error al obtener historial de teléfonos.");
        }
    }

    public void crearTelefono(TelefonoModelCli telefonoModelCli) {
        try {
            Person person = personInputPort.findOne(telefonoModelCli.getDuenioId());
            Phone phone = phoneInputPort.create(telefonoMapperCli.fromAdapterToDomain(telefonoModelCli, person));
            System.out.println("Teléfono creado exitosamente");
            System.out.println(phone);
        } catch (Exception e) {
            System.out.println("El teléfono no ha podido ser creado");
        }
    }

    public void obtenerTelefono(String numeroTelefono) {
        try {
            System.out.println(phoneInputPort.findOne(numeroTelefono));
        } catch (Exception e) {
            System.out.println("El teléfono con número " + numeroTelefono + " no existe en el sistema");
        }
    }

    public void editarTelefono(TelefonoModelCli telefonoModelCli) {
        try {
            Person person = personInputPort.findOne(telefonoModelCli.getDuenioId());
            Phone phone = phoneInputPort.edit(telefonoModelCli.getNumero(),
                    telefonoMapperCli.fromAdapterToDomain(telefonoModelCli, person));
            System.out.println("Teléfono editado exitosamente");
            System.out.println(phone);
        } catch (Exception e) {
            System.out.println("El teléfono no ha podido ser editado");
        }
    }

    public void eliminarTelefono(String numeroTelefono) {
        try {
            phoneInputPort.drop(numeroTelefono);
            System.out.println("Teléfono con número " + numeroTelefono + " ha sido eliminado");
        } catch (Exception e) {
            System.out.println("El teléfono no ha podido ser eliminado");
        }
    }
}