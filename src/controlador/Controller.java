package controlador;

import modelo.Instrumento;
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
                6. Mostrar un instrumento segun sus caracteristicas
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
//                0. Salir
                    salir = true;
                    break;
                case 1:
//                1. Leer un fichero txt y almacenar sus datos en un ArrayList
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    ir.readTxtToArray(rOrigen);

                    vista.textoPantalla("Datos guardados en ArrayList: ");

                    vista.textoPantalla(ir.mostrarInstrumentos());
                    vista.textoPantalla("");
                    break;
                case 2:
//                2. Escribir objetos del fichero txt en un fichero binario
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla("Introduce la ruta del fichero binario");
                    rDestino = sc.nextLine();

                    ir.escribirFicheroBinObjetos(rOrigen, rDestino);
                    vista.textoPantalla("Datos guardados en el fichero " + rDestino + ":");
                    vista.textoPantalla(ir.leerArchivoDatos(rDestino));
                    break;
                case 3:
//                3. Escribir datos del fichero txt en un fichero binario
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla("Introduce la ruta del fichero binario");
                    rDestino = sc.nextLine();

                    ir.escribirFicheroBinarioDatos(rOrigen, rDestino);
                    vista.textoPantalla("Datos guardados en el fichero " + rDestino + ":");
                    vista.textoPantalla(ir.mostrarInstrumentos());
                    break;
                case 4:
//                4. Escribir datos en un fichero aleatorio
                    vista.textoPantalla("Introduce la ruta del fichero txt");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla("Introduce la ruta del fichero aleatorio");
                    rDestino = sc.nextLine();

                    ir.escribirFicheroAleatorio(rOrigen, rDestino);
                    vista.textoPantalla("Datos guardados en el fichero " + rDestino);
                    break;
                case 5:
//                5. Leer datos de un fichero aleatorio
                    vista.textoPantalla("Introduce la ruta del fichero aleatorio");
                    rOrigen = sc.nextLine();

                    ir.leerFicheroAleatorio(rOrigen);

                    vista.textoPantalla(ir.comprobador(ir.leerFicheroAleatorio(rOrigen)));
                    break;
                case 6:
//                6. Mostrar un instrumento segun sus caracteristicas
                    vista.textoPantalla("Introduce la ruta del fichero aleatorio");
                    rOrigen = sc.nextLine();
                    vista.textoPantalla(ir.comprobador(ir.leerFicheroAleatorio(rOrigen)));

                    vista.textoPantalla("Que datos quieres buscar?");
                    int updateOpcion = updateMenu();

                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    public int updateMenu() {
        String menu = """
                1. Nombre
                2. Tipo
                3. Origen
                4. Material
                5. Precio
                """;
        vista.textoPantalla(menu);
        int opcion = sc.nextInt();
        sc.nextLine();

        return opcion;
    }

    public <E> E actualizador(int opcion) {
        switch (opcion) {
            case 1:
                // Nombre
                String nombre = sc.nextLine();
                return (E) nombre;
            case 2:
                // tipo
                String tipo = sc.nextLine();
                return (E) tipo;
            case 3:
                // Origen
                String origen = sc.nextLine();
                return (E) origen;
            case 4:
                String material = sc.nextLine();
                return (E) material;
            case 5:
                Double precio = sc.nextDouble();
                return (E) precio;
            default:
                return null;
        }
    }

}
