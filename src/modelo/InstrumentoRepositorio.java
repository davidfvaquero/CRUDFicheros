package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstrumentoRepositorio {

    private final int NOMBRE_SIZE = 30;
    private final int TIPO_SIZE = 30;
    private final int ORIGEN_SIZE = 30;
    private final int MATERIAL_SIZE = 30;
    private final int PRECIO_SIZE = 8;

    private final int INSTRUMENTO_SIZE = NOMBRE_SIZE + TIPO_SIZE +
            ORIGEN_SIZE + MATERIAL_SIZE + PRECIO_SIZE;

    StringBuilder nombre = new StringBuilder();
    StringBuilder tipo = new StringBuilder();
    StringBuilder origen = new StringBuilder();
    StringBuilder material = new StringBuilder();
    double precio = 0.0;

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

        mostrarInstrumentos();

        return listaInstrumentos;
    }

    /**
     * Muestra los elementos guardados en el ArrayList
     *
     * @return Srting con los elementos guardados en el ArrayList
     */
    public String mostrarInstrumentos() {
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

    /**
     * lee de un fichero txt y escribe los datos en un fichero .dat
     *
     * @param rOrigen  String con la ruta del fichero txt
     * @param rDestino String con la ruta del fichero dat
     */
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

    /**
     * lee de un fichero txt y escribe los datos en un fichero aleatorio
     *
     * @param rOrigen  String con la ruta del fichero txt
     * @param rDestino String con el fichero aleatorio
     */
    public void escribirFicheroAleatorio(String rOrigen, String rDestino) {
        listaInstrumentos = readTxtToArray(rOrigen);

        nombre.setLength(NOMBRE_SIZE / 2);
        tipo.setLength(TIPO_SIZE / 2);
        origen.setLength(ORIGEN_SIZE / 2);
        material.setLength(MATERIAL_SIZE / 2);

        File f = new File(rDestino);
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(f, "rw");
            raf.seek(0);

            for (int i = 0; i < listaInstrumentos.size(); i++) {
                raf.writeUTF(listaInstrumentos.get(i).getNombre());
                raf.writeUTF(listaInstrumentos.get(i).getTipo());
                raf.writeUTF(listaInstrumentos.get(i).getOrigen());
                raf.writeUTF(listaInstrumentos.get(i).getMaterial());
                raf.writeDouble(listaInstrumentos.get(i).getPrecio());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * escribe en un fichero aleatorio los datos del instrumento introducido
     *
     * @param raf RandomAccessFile del fichero aleatorio
     * @param instrumento Instrumento a escribir
     */
    public void escribirFicheroAleatorio(RandomAccessFile raf, Instrumento instrumento) {

        try {
            raf.writeUTF(instrumento.getNombre());
            raf.writeUTF(instrumento.getTipo());
            raf.writeUTF(instrumento.getOrigen());
            raf.writeUTF(instrumento.getMaterial());
            raf.writeDouble(instrumento.getPrecio());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** lee los datos del fichero aleatorio y los almacena en un ArrayList<Instrumento>
     *
     * @param ruta String con la ruta del archivo a leer
     * @return ArrayList<Instrumento> con los datos de cada instrumento
     */
    public ArrayList<Instrumento> leerFicheroAleatorio(String ruta) {
        listaInstrumentos = new ArrayList<>();

        File f;
        RandomAccessFile raf = null;
        long pos = 0;

        try {
            f = new File(ruta);
            raf = new RandomAccessFile(f, "r");

            while (raf.getFilePointer() < raf.length()) {

                String nombre = raf.readUTF();
                String tipo = raf.readUTF();
                String origen = raf.readUTF();
                String material = raf.readUTF();
                double precio = raf.readDouble();

                Instrumento i = new Instrumento(nombre, tipo, origen, material, precio);
                listaInstrumentos.add(i);
            }

        } catch (FileNotFoundException | EOFException e) {
            Logger.getLogger(InstrumentoRepositorio.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(InstrumentoRepositorio.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                Logger.getLogger(InstrumentoRepositorio.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return listaInstrumentos;
    }

    /**
     *  Lee un ArrayList<Instrumento> y devuelve el contenido en una String
     * @param instrumentos ArrayList con los datos
     * @return String con los datos formateados
     */
    public String comprobador(ArrayList<Instrumento> instrumentos) {
        String aux = "";

        for (Instrumento instrumento : instrumentos) {
            aux += instrumento.toString() + "\n";
        }

        return aux;
    }

    public <E> String mostrarInstrumentosCaracteristicas(E caracteristica, String ruta, int opcion) {
        listaInstrumentos = leerFicheroAleatorio(ruta);

        String aux = "";

        switch (opcion) {
            case 1:
                for (Instrumento instrumento : listaInstrumentos) {
                    if (instrumento.getNombre() == (String) caracteristica && instrumento.getNombre() != "") {
                        aux += instrumento.toString() + "\n";
                    }
                }
                break;
            case 2:
                for (Instrumento instrumento : listaInstrumentos) {
                    if (instrumento.getTipo() == (String) caracteristica && instrumento.getTipo() != "") {
                        aux += instrumento.toString() + "\n";
                    }
                }
                break;
            case 3:
                for (Instrumento instrumento : listaInstrumentos) {
                    if (instrumento.getOrigen() == (String) caracteristica && instrumento.getOrigen() != "") {
                        aux += instrumento.toString() + "\n";
                    }
                }
                break;
            case 4:
                for (Instrumento instrumento : listaInstrumentos) {
                    if (instrumento.getMaterial() == (String) caracteristica && instrumento.getMaterial() != "") {
                        aux += instrumento.toString() + "\n";
                    }
                }
                break;
            case 5:
                for (Instrumento instrumento : listaInstrumentos) {
                    if (instrumento.getPrecio() == (double) caracteristica && instrumento.getPrecio() != 0) {
                        aux += instrumento.toString() + "\n";
                    }
                }
                break;
        }
        if (aux.isEmpty()) {
            return "No hay instrumentos con estas caracteristicas";
        }

        return aux.trim() + "\n";
    }
}
