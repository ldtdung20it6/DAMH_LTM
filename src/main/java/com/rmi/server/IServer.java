/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author TRUNG DUNG
 */
public interface IServer extends Remote{
    public Boolean login(String email,String password) throws RemoteException;
    public String getName() throws RemoteException;
    public int withdrawMoney(int withdraw) throws RemoteException;
    public int withdrawMoneyInDatabaseUser(int withdraw) throws RemoteException;
    public int totalMoneyInDatabaseUser() throws RemoteException;
    public int totalMoneyInBank() throws RemoteException;
    public void dongBo() throws RemoteException;
    public int dePosit(int money) throws RemoteException;
    public void changePassword(String newPassword,String inPutPasswordOld) throws RemoteException;
    public void dongBoPassword() throws RemoteException;
}
