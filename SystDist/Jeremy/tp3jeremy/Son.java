package tp3jeremy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Son extends Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    public Son() {
        System.out.println("Son()");
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        Son son = new Son();

        System.out.println("Start of serialization");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(son);

        System.out.println("End of serialization");

        System.out.println("Start of deserialization");

        ObjectInputStream ois =
                new ObjectInputStream(new ByteArrayInputStream(
                        baos.toByteArray()));
        Son son2 = (Son) ois.readObject();

        System.out.println("End of deserialization");
    }

}
