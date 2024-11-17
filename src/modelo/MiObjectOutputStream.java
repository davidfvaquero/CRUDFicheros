package modelo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public MiObjectOutputStream() throws IOException, SecurityException {
    }

    @Override
    protected void writeStreamHeader() throws IOException {
    }
}
