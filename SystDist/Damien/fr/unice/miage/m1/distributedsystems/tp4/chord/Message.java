package fr.unice.miage.m1.distributedsystems.tp4.chord;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Message extends Serializable {

	void handle(Peer peer) throws RemoteException;
}
