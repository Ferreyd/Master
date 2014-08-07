package tp5nicolas.rmi_basics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyObject implements Serializable
{

    private static final long serialVersionUID = 1L;

    private int field;

    public int getField()
    {
        return this.field;
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        System.out.println("MyObject.writeObject()");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        System.out.println("MyObject.readObject()");
    }

}
