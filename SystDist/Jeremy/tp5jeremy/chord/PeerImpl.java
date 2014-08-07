package tp5jeremy.chord;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PeerImpl extends UnicastRemoteObject implements Comparable<Peer>,
        Peer {

    private static final long serialVersionUID = 1L;

    private final Identifier id;

    private final Map<String, String> directory;

    private Peer predecessor;

    private Peer successor;
    
    private Identifier leaderId;
    
    private boolean participant;

    public PeerImpl(Identifier id) throws RemoteException {
        this.id = id;
        this.predecessor = this;
        this.successor = this;

        this.directory = new HashMap<String, String>();

        ScheduledExecutorService threadPool =
                Executors.newScheduledThreadPool(1);

        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    PeerImpl.this.stabilize();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public synchronized void create() throws RemoteException {
        this.predecessor = null;
        this.successor = this;
    }

    @Override
    public synchronized void join(Peer landmarkPeer) throws RemoteException {
        this.predecessor = null;
        this.successor = landmarkPeer.findSuccessor(this.id);
    }

    @Override
    public Peer findSuccessor(Identifier id) throws RemoteException {
        if (this.successor.equals(this)) {
            return this;
        }

        if (id.isBetweenOpenClosed(this.id, this.successor.getId())) {
            return this.successor;
        } else {
            return this.successor.findSuccessor(id);
        }
    }

    @Override
    public Identifier getId() throws RemoteException {
        return this.id;
    }

    @Override
    public Peer getPredecessor() throws RemoteException {
        return this.predecessor;
    }

    @Override
    public Peer getSuccessor() throws RemoteException {
        return this.successor;
    }

    @Override
    public synchronized void setPredecessor(Peer peer) throws RemoteException {
        this.predecessor = peer;
    }

    @Override
    public synchronized void setSuccessor(Peer peer) throws RemoteException {
        this.successor = peer;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return this.id.equals(((Peer) obj).getId());
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public int compareTo(Peer p) {
        try {
            return this.id.compareTo(p.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public synchronized void stabilize() throws RemoteException {
        Peer x = this.successor.getPredecessor();

        if (x != null
                && x.getId().isBetweenOpenOpen(this.id, this.successor.getId())) {
            this.successor = x;
        }

        this.successor.notify(PeerImpl.this);
    }

    @Override
    public synchronized void notify(Peer peer) throws RemoteException {
        if (this.predecessor == null
                || peer.getId().isBetweenOpenOpen(
                        this.predecessor.getId(), this.id)) {
            this.predecessor = peer;
        }
    }

    @Override
    public synchronized void put(String name, String phoneNumber)
            throws RemoteException {
        this.directory.put(name, phoneNumber);
    }

    @Override
    public String get(String name) throws RemoteException {
        return this.directory.get(name);
    }

	@Override
	public void startElection() throws RemoteException {
        this.participant = true;
        this.successor.receive(new ElectionMessage(this.id));
		
	}

	public void setLeaderId(Identifier leaderId) {
		this.leaderId = leaderId;
	}

	public Identifier getLeaderId() {
		return leaderId;
	}

	public void setParticipant(boolean participant) {
		this.participant = participant;
	}

	public boolean isParticipant() {
		return participant;
	}

	@Override
	public void receive(Message msg) throws RemoteException {
		// TODO Auto-generated method stub
		msg.handle(this);
		
	}

}
