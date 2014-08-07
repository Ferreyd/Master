package tp4nicolas.chord;

import tp4nicolas.chord.exceptions.AlreadyRegisteredException;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackerImpl extends UnicastRemoteObject implements Tracker
{

    private static final long serialVersionUID = 1L;

    private final List<Peer> peers;

    private final Random randomGenerator;

    public TrackerImpl(int port) throws RemoteException, MalformedURLException, AlreadyBoundException
    {
        this.peers = new ArrayList<Peer>();
        this.randomGenerator = new Random();

        Registry registry = LocateRegistry.createRegistry(port);

        registry.bind("//localhost:" + port + "/tracker", this);
    }

    @Override
    public synchronized void register(Peer peer) throws AlreadyRegisteredException, RemoteException
    {
        // should use a HashSet or ConcurrentHashMap for better performance
        if(this.peers.contains(peer))
        {
            throw new AlreadyRegisteredException(peer.getId());
        }

        this.peers.add(peer);
    }

    @Override
    public Peer getRandomPeer() throws RemoteException
    {
        if(this.peers.isEmpty())
        {
            return null;
        }

        return this.peers.get(this.randomGenerator.nextInt(this.peers.size()));
    }

}
