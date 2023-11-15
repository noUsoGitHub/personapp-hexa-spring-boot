package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.javeriana.as.personapp.terminal.adapter.EstudiosInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.ProfesionInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.TelefonoInputAdapterCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuPrincipal {

    // Constantes para mensajes
    private static final String SOLO_NUMEROS = "Solo se permiten números.";
    private static final String INGRESE_OPCION = "Ingrese una opción: ";

    // Beans
    @Autowired
    private PersonaInputAdapterCli personaInputAdapterCli;

    @Autowired
    private ProfesionInputAdapterCli profesionInputAdapterCli;

    @Autowired
    private TelefonoInputAdapterCli telefonoInputAdapterCli;

    @Autowired
    private EstudiosInputAdapterCli estudiosInputAdapterCli;

    private static final int SALIR = 0;
    private static final int MODULO_PERSONA = 1;
    private static final int MODULO_PROFESION = 2;
    private static final int MODULO_TELEFONO = 3;
    private static final int MODULO_ESTUDIO = 4;

    // Menus
    private final PersonaMenu personaMenu;
    private final ProfesionMenu profesionMenu;
    private final TelefonoMenu telefonoMenu;
    private final EstudiosMenu estudiosMenu;
    private final Scanner keyboard;

    public MenuPrincipal(PersonaMenu personaMenu, ProfesionMenu profesionMenu, TelefonoMenu telefonoMenu,
            EstudiosMenu estudiosMenu) {
        this.personaMenu = personaMenu;
        this.profesionMenu = profesionMenu;
        this.telefonoMenu = telefonoMenu;
        this.estudiosMenu = estudiosMenu;
        this.keyboard = new Scanner(System.in);
    }

    public void inicio() {
        boolean isValid = false;
        do {
            try {
                mostrarMenu();
                int opcion = leerOpcion();
                switch (opcion) {
                    case SALIR:
                        isValid = true;
                        break;
                    case MODULO_PERSONA:
                        personaMenu.iniciarMenu(personaInputAdapterCli, keyboard);
                        log.info("Volvió al menú principal");
                        break;
                    case MODULO_PROFESION:
                        profesionMenu.iniciarMenu(profesionInputAdapterCli, keyboard);
                        break;
                    case MODULO_TELEFONO:
                        telefonoMenu.iniciarMenu(telefonoInputAdapterCli, keyboard);
                        break;
                    case MODULO_ESTUDIO:
                        estudiosMenu.iniciarMenu(estudiosInputAdapterCli, keyboard);
                        break;
                    default:
                        log.warn("La opción elegida no es válida.");
                }
            } catch (NoSuchElementException e) {
                log.warn("Ocurrió un error al leer la entrada del teclado.");
            }
        } while (!isValid);
        keyboard.close();
    }

    private void mostrarMenu() {
        System.out.println("----------------------");
        System.out.println(MODULO_PERSONA + " para trabajar con el Modulo de Personas");
        System.out.println(MODULO_PROFESION + " para trabajar con el Modulo de Profesiones");
        System.out.println(MODULO_TELEFONO + " para trabajar con el Modulo de Telefonos");
        System.out.println(MODULO_ESTUDIO + " para trabajar con el Modulo de Estudios");
        System.out.println(SALIR + " para Salir");
    }

    private int leerOpcion() {
        try {
            System.out.print(INGRESE_OPCION);
            int opcion = keyboard.nextInt();
            keyboard.nextLine(); // Consumir el carácter de nueva línea pendiente en el buffer
            return opcion;
        } catch (InputMismatchException e) {
            log.warn(SOLO_NUMEROS);
            keyboard.nextLine(); // Limpiar el buffer del teclado
            return leerOpcion();
        }
    }
}
