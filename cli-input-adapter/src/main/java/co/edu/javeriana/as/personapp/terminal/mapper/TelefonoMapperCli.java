package co.edu.javeriana.as.personapp.terminal.mapper;

import java.util.Objects;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

@Mapper
public class TelefonoMapperCli {

    public TelefonoModelCli fromDomainToAdapterCli(Phone phone) {
        Objects.requireNonNull(phone, "Phone cannot be null");

        return new TelefonoModelCli(
            phone.getNumber(),
            phone.getCompany(),
            phone.getOwner() != null ? phone.getOwner().getIdentification() : null
        );
    }

    public Phone fromAdapterToDomain(TelefonoModelCli telefonoModelCli, Person owner) {
        Objects.requireNonNull(telefonoModelCli, "TelefonoModelCli cannot be null");
        Objects.requireNonNull(owner, "Owner cannot be null");

        Phone phone = new Phone();
        phone.setNumber(telefonoModelCli.getNumero());
        phone.setOwner(owner);
        phone.setCompany(telefonoModelCli.getCompannia());
        return phone;
    }
}
