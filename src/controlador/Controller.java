package controlador;

import modelo.InstrumentoRepositorio;
import vista.Vista;

import java.util.Scanner;

public class Controller {
    Vista vista;
    InstrumentoRepositorio ir;

    public static Scanner sc = new Scanner(System.in);

    public Controller(Vista vista, InstrumentoRepositorio ir) {
        this.vista = vista;
        this.ir = ir;
        procesarOpcion();
    }

    public int menu() {
        int opcion;

        vista.textoPantalla("1. Leer un fichero txt y almacenar sus datos en un ArrayList " + "\n"
                + "0. Salir");
        opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    public void procesarOpcion() {
        boolean salir = false;
        String rOrigen, rDestino;
        int opcion;

        while (!salir) {
            opcion = menu();
            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    ir.readTxtToArray(rOrigen);

                    vista.textoPantalla("Datos guardados en ArrayList: ");

                    vista.textoPantalla(ir.mostarInstrumentos());
                    vista.textoPantalla("");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
