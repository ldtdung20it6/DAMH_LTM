/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rmi.client;

import com.rmi.server.ServerImp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author TRUNG DUNG
 */
public class ClientImp extends UnicastRemoteObject implements IClient{
    ServerImp serverImp;
    
    public ClientImp() throws RemoteException{
        super();
    }

    @Override
    public boolean login(String email,String password) throws RemoteException {
        serverImp.login(email, password);
        if(serverImp.login(email, password) == true){
            return true;
        }
        return false;
    }
}
