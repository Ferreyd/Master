package tp3jeremy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GrandSonCustomSerialization extends Son2 implements Serializable {

    private static final long serialVersionUID = 1L;

    public GrandSonCustomSerialization() {
        System.out.println("GrandSon()");
    }

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        GrandSonCustomSerialization grandSon =
                new GrandSonCustomSerialization();
        grandSon.value = 7;

        System.out.println("Start of serialization");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(grandSon);

        System.out.println("End of serialization");

        System.out.println("Start of deserialization");

        ObjectInputStream ois =
                new ObjectInputStream(new ByteArrayInputStream(
                        baos.toByteArray()));
        GrandSonCustomSerialization grandSon2 =
                (GrandSonCustomSerialization) ois.readObject();

        System.out.println("grandSon2.value=" + grandSon2.value);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(super.value);
        System.out.println("SonCustomSerialization.writeObject()");
    }

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        super.value = in.readInt();
        System.out.println("SonCustomSerialization.readObject()");
    }

}
