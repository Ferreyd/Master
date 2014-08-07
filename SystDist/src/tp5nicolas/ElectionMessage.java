package tp5nicolas;

import tp5nicolas.chord.Identifier;
import tp5nicolas.chord.Message;
import tp5nicolas.chord.Peer;

import java.rmi.RemoteException;

/**
 * @author Nicolas
 *         Date : 18/11/13 14:06
 */
public class ElectionMessage implements Message
{
    private Identifier leaderId;

    public ElectionMessage(Identifier coordinator)
    {
        this.leaderId = coordinator;
    }

    @Override
    public void handle(Peer peer) throws RemoteException
    {
        int comparison = this.leaderId.compareTo(peer.getId());

        System.out.println("Handling election message on peer with id " + peer.getId() + ", " +
                           "" + this.leaderId + ".compareTo(" + peer.getId() + ")=" + comparison + ", " +
                           "participate? " + peer.isParticipant());

        if(comparison > 0)
        {
            peer.getSuccessor().receive(this);
        }else if(comparison < 0 && !peer.isParticipant())
        {
            peer.setParticipant(true);
            this.leaderId = peer.getId();
            peer.getSuccessor().receive(this);
        }else if(comparison < 0 && peer.isParticipant())
        {
            // do nothing, discards the election message.
        }else if(comparison == 0)
        {
            peer.setLeaderId(peer.getId());
            System.out.println("Leader found: " + this.leaderId);
            peer.getSuccessor().receive(new ElectedMessage(this.leaderId));
        }
    }
}
