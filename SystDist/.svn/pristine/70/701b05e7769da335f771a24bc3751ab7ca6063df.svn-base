package fr.unice.miage.m1.distributedsystems.tp4.chord;

import java.rmi.RemoteException;

public class ElectionMessage implements Message {

	private Identifier coordinateurId;
	
	private Identifier peersId;

	public ElectionMessage(Identifier coordinateurId) {
		super();
		this.coordinateurId = coordinateurId;
	}

	@Override
	public void handle(Peer peer) throws RemoteException {
		int comparaison = this.coordinateurId.compareTo(peer.getId());
		
		 System.out.println("Handling election message on peer with id "
	                + peer.getId() + ", " + this.coordinateurId + ".compareTo("
	                + peer.getId() + ")=" + comparaison + ", participate? "
	                + peer.getParticipant());

	        if (comparaison > 0) {
	            peer.getSuccessor().receive(this);
	        } else if (comparaison < 0 && !peer.getParticipant()) {
	            peer.setParticipant(true);
	            this.coordinateurId = peer.getId();
	            peer.getSuccessor().receive(this);
	        } else if (comparaison < 0 && peer.getParticipant()) {
	            // do nothing, discards the election message.
	        } else if (comparaison == 0) {
	            peer.setLeaderId(peer.getId());
	            System.out.println("Leader found: " + this.coordinateurId);
	            //peer.getSuccessor().receive(new ElectedMessage(this.coordinateurId));
	        }
	}
	
}
