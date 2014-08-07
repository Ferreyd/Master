package tp5jeremy.chord.exceptions;

import tp5jeremy.chord.Identifier;

public class AlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public AlreadyRegisteredException(Identifier id) {
        super("Peer with ID " + id + " already registered");
    }

}
