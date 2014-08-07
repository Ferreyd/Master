package fr.unice.miage.m1.distributedsystems.tp4.directory;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectoryImpl extends UnicastRemoteObject implements Directory {

    private static final long serialVersionUID = 1L;

    private static final Map<String, String> entries =
            new HashMap<String, String>();

    private static final int NB_OPERATIONS = (int) 10e3; // = 10^3

    protected DirectoryImpl() throws RemoteException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String name, String phoneNumber) throws RemoteException {
        entries.put(name, phoneNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(String name) throws RemoteException {
        return entries.get(name);
    }

    public static void main(String[] args) throws RemoteException,
            MalformedURLException, AlreadyBoundException, NotBoundException {
        int rmiRegistryPort = 1099;

        // the rmiregistry can either be created programmatically or started
        // manually before each run if you have some time to waste
        LocateRegistry.createRegistry(rmiRegistryPort);
        System.out.println("RMI registry listening on port " + rmiRegistryPort);

        Directory directory = new DirectoryImpl();
        Naming.bind("directory", directory);

        final Directory directoryStub =
                (Directory) Naming.lookup("rmi://localhost/directory");

        // sequentialPutGet(directoryStub);

        // to fix the race condition on the put method you could either
        // synchronize the put method or use a ConcurrentHashMap
        multithreadedPutGet(directoryStub);
    }

    @SuppressWarnings("unused")
    private static void sequentialPutGet(Directory stub) {
        for (int i = 0; i < NB_OPERATIONS; i++) {
            String name = "name" + i;

            try {
                stub.put(name, String.format("049373%04d", i));

                System.out.println("Phone number for " + name + " is "
                        + stub.get(name));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private static void multithreadedPutGet(final Directory stub) {
        ExecutorService threadPool =
                Executors.newFixedThreadPool(Runtime.getRuntime()
                        .availableProcessors());

        for (int i = 0; i < NB_OPERATIONS; i++) {
            final int index = i;

            threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    String name = "name" + index;

                    try {
                        stub.put(name, String.format("049373%04d", index));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        for (int i = 0; i < NB_OPERATIONS; i++) {
            String name = "name" + i;

            try {
                System.out.println("Phone number for " + name + " is "
                        + stub.get(name));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();
    }

}
