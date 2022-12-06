/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author TRUNG DUNG
 */
public interface IClient extends Remote{
    public boolean login(String email,String password) throws RemoteException;
}
