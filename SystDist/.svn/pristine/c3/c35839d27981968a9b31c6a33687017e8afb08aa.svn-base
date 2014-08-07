package tp3jeremy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ParentCustomSerialization {

    public ParentCustomSerialization() {
        System.out.println("Parent()");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        System.out.println("ParentCustomSerialization.writeObject()");
    }

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("ParentCustomSerialization.readObject()");
    }

}
