package modelo;

import java.io.*;
import java.util.ArrayList;

public class InstrumentoRepositorio {

    ArrayList<Instrumento> listaInstrumentos;

    /**
     * Lee los datos de un txt y los almacena en un array
     *
     * @param ruta String con la ruta del archivo a leer
     * @return un ArrayList con los instrumentos leidos
     */
    public ArrayList<Instrumento> readTxtToArray(String ruta) {
        listaInstrumentos = new ArrayList<>();
        String mostrarDatos;

        File f = new File(ruta);
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            br.readLine(); // Lee nombre, tipo, origen, material, precio
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String tipo = datos[1];
                String origen = datos[2];
                String material = datos[3];
                double precio = Double.parseDouble(datos[4]);

                Instrumento i = new Instrumento(nombre, tipo, origen, material, precio);
                listaInstrumentos.add(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mostarInstrumentos();

        return listaInstrumentos;
    }

    public String mostarInstrumentos() {
        String aux = "";

        for (Instrumento instrumento : listaInstrumentos) {
            aux += instrumento.toString() + "\n";
        }

        return aux;
    }
}
