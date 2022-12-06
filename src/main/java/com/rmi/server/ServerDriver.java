/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author TRUNG DUNG
 */
public class ServerDriver {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        ServerUI serverUI = new ServerUI();
        serverUI.setVisible(true);
        String url = "rmi://localhost:2909/ATM_RMI";
        ServerImp server = new ServerImp();
        int port = 2909;
        // server1
        LocateRegistry.createRegistry(port);
        Naming.rebind(url, server);
        System.out.println("[System] Server 1 is ready...");

        //server2
        LocateRegistry.createRegistry(2310);
        Naming.rebind(url, server);
        System.out.println("[System] Server 2 is ready...");

        //server3
        LocateRegistry.createRegistry(0202);
        Naming.rebind(url, server);
        System.out.println("[System] Server 3 is ready...");
    }
}
