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

        vista.textoPantalla("""
                1. Leer un fichero txt y almacenar sus datos en un ArrayList
                2. Escribir objetos del fichero txt en un fichero binario
                3. Escribir datos del fichero txt en un fichero binario
                4. Escribir datos en un fichero aleatorio
                5. Leer datos de un fichero aleatorio
                0. Salir""");
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
                case 2:
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla("Introduce la ruta del fichero binario");
                    rDestino = sc.nextLine();

                    ir.escribirFicheroBinObjetos(rOrigen, rDestino);
                    vista.textoPantalla("Datos guardados en el fichero " + rDestino + ":");
                    vista.textoPantalla(ir.leerArchivoDatos(rDestino));
                    break;
                case 3:
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla("Introduce la ruta del fichero binario");
                    rDestino = sc.nextLine();

                    ir.escribirFicheroBinarioDatos(rOrigen, rDestino);
                    vista.textoPantalla("Datos guardados en el fichero " + rDestino + ":");
                    vista.textoPantalla(ir.mostarInstrumentos());
                    break;
                case 4:
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla("Introduce la ruta del fichero aleatorio");
                    rDestino = sc.nextLine();

                    ir.escribirFicheroAleatorio(rOrigen, rDestino);
                    vista.textoPantalla("Datos guardados en el fichero " + rDestino);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
