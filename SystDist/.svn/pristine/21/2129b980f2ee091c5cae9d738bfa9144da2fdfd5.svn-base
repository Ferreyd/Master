package tp3jeremy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GrandSon extends Son2 implements Serializable {

    private static final long serialVersionUID = 1L;

    public GrandSon() {
        System.out.println("GrandSon()");
    }

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        GrandSon grandSon = new GrandSon();
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
        GrandSon grandSon2 = (GrandSon) ois.readObject();

        System.out.println("grandSon2.value=" + grandSon2.value);

    }

}
