package tp5jeremy.chord;

import java.rmi.RemoteException;

public class ElectionMessage implements Message{

	
	private Identifier leaderId;
	
    public ElectionMessage(Identifier coordinator) {
        this.leaderId = coordinator;
    }
	@Override
	public void handle(Peer peer) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	
}
