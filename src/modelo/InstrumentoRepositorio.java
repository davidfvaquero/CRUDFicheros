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
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        mostarInstrumentos();

        return listaInstrumentos;
    }

    /**
     * Muestra los elementos guardados en el ArrayList
     *
     * @return Srting con los elementos guardados en el ArrayList
     */
    public String mostarInstrumentos() {
        String aux = "";

        for (Instrumento instrumento : listaInstrumentos) {
            aux += instrumento.toString() + "\n";
        }

        return aux;
    }

    /**
     * Lee de un fichero de texto y lo guarda los objetos en otro fichero binario
     *
     * @param rOrigen  String con la ruta del fichero de texto
     * @param rDestino String con la ruta del fichero binario
     */
    public void escribirFicheroBinObjetos(String rOrigen, String rDestino) {
        listaInstrumentos = readTxtToArray(rOrigen);
        File f = new File(rDestino);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            if (f.length() > 0) {
                fos = new FileOutputStream(f, true);
                oos = new MiObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(rDestino);
                oos = new ObjectOutputStream(fos);
            }

            for (Instrumento instrumento : listaInstrumentos) {
                oos.writeObject(instrumento);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        leerArchivoDatos(rDestino);
    }

    /**
     * Lee archivos binarios en los que hay objetos almacenados
     *
     * @param ruta String con la ruta del archivo a leer
     * @return String con los datos del archivo
     */
    public String leerArchivoDatos(String ruta) {
        String aux = "";

        File f = new File(ruta);

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);

            while (ois.readObject() != null) {
                Instrumento instrumento = (Instrumento) ois.readObject();
                aux += instrumento.toString() + "\n";
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return aux;
    }

    public void escribirFicheroBinarioDatos(String rOrigen, String rDestino) {
        listaInstrumentos = readTxtToArray(rOrigen);

        File f = new File(rDestino);
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            if (f.length() > 0) {
                fos = new FileOutputStream(f, true);
            } else {
                fos = new FileOutputStream(f);
            }
            dos = new DataOutputStream(fos);
            for (Instrumento instrumento : listaInstrumentos) {
                dos.writeUTF(instrumento.getNombre());
                dos.writeUTF(instrumento.getTipo());
                dos.writeUTF(instrumento.getOrigen());
                dos.writeUTF(instrumento.getMaterial());
                dos.writeDouble(instrumento.getPrecio());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dos.close();
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
