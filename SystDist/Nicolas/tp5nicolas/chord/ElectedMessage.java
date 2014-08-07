package tp5nicolas.chord;


import java.rmi.RemoteException;

public class ElectedMessage implements Message
{

    private static final long serialVersionUID = 1L;

    private final Identifier leaderId;

    public ElectedMessage(Identifier leaderId)
    {
        this.leaderId = leaderId;
    }

    @Override
    public void handle(Peer peer) throws RemoteException
    {
        if(!peer.getId().equals(this.leaderId))
        {
            System.out.println("Peer with id " + peer.getId() + " notified about new leader: " + this.leaderId);
            peer.setLeaderId(this.leaderId);
            peer.getSuccessor().receive(this);
        }
    }

}
