package principal;

import controlador.Controller;
import modelo.InstrumentoRepositorio;
import vista.Vista;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        InstrumentoRepositorio instrumentoRepositorio = new InstrumentoRepositorio();
        Controller controller = new Controller(vista, instrumentoRepositorio);
    }
}