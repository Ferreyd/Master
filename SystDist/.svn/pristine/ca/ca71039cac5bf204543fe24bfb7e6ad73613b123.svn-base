package tp3nicolas.serialization;

import java.io.*;

public class SonCustomSerialization extends Parent implements Serializable
{

    private static final long serialVersionUID = 1L;

    public SonCustomSerialization()
    {
        System.out.println("Son()");
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {

        SonCustomSerialization son = new SonCustomSerialization();

        System.out.println("Start of serialization");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(son);

        System.out.println("End of serialization");

        System.out.println("Start of deserialization");

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        SonCustomSerialization son2 = (SonCustomSerialization) ois.readObject();

        System.out.println("End of deserialization");
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        System.out.println("SonCustomSerialization.writeObject()");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        System.out.println("SonCustomSerialization.readObject()");
    }

}
