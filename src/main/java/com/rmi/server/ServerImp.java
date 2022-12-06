/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rmi.server;

import com.rmi.client.IClient;
import com.rmi.database.Database;
import com.rmi.model.Bank;
import com.rmi.model.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TRUNG DUNG
 */
public class ServerImp extends UnicastRemoteObject implements IServer {

    User user = new User();
    Bank bank = new Bank();
    IClient iClient;

    public ServerImp() throws RemoteException {
        super();
    }

    @Override
    public Boolean login(String email, String password) throws RemoteException {
        try {
            Statement statement = Database.databaseMB().createStatement();
            String sql = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int totalmoney = resultSet.getInt("totalMoney");
                user.setId(id);
                user.setName(name);
                user.setTotalMoney(totalmoney);
                user.setEmail(email);
                user.setPassword(password);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public int withdrawMoney(int withdraw) throws RemoteException {
        int sum = totalMoneyInBank() - withdraw;
        if (totalMoneyInBank() - withdraw >= 0) {
            System.out.println("thanh cong");
            bank.setTotalMoneyInBank(sum);
        } else {
            System.out.println("bank khong con tien");
        }
        return sum;
    }

    @Override
    public int withdrawMoneyInDatabaseUser(int withdraw) throws RemoteException {
        int sum = 0;
        try {
            Statement statement = Database.databaseMB().createStatement();
            String sql = "SELECT * FROM user WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int totalMoney = resultSet.getInt("totalMoney");
                sum = totalMoney - withdraw;
                if (sum >= 0) {
                    Statement statementSum = Database.databaseMB().createStatement();
                    String sqlSum = " UPDATE `user` SET `totalMoney`='" + sum + "' WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "' ";
                    int resultSetSum = statementSum.executeUpdate(sqlSum);
                    System.out.println("Thanh Cong database");
                    user.setTotalMoney(sum);
                } else {
                    System.out.println("ban khong du tien");
                }
            }
        } catch (Exception e) {
        }
        return sum;
    }

    @Override
    public String getName() throws RemoteException {
        return user.getName();
    }

    @Override
    public int totalMoneyInDatabaseUser() throws RemoteException {
        return user.getTotalMoney();
    }

    @Override
    public int totalMoneyInBank() throws RemoteException {
        return bank.getTotalMoneyInBank();
    }

    @Override
    public void dongBo() throws RemoteException {
        int money = totalMoneyInDatabaseUser();
        System.out.println(money);
        if (money >= 0) {
            try {
                // dong bo mien trung
                Statement statementMT = Database.databaseMT().createStatement();
                String sqlMT = " UPDATE `user` SET `totalMoney`='" + money + "' WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "' ";
                int resultSetMT = statementMT.executeUpdate(sqlMT);
                // dong bo mien nam
                Statement statementMN = Database.databaseMN().createStatement();
                String sqlMN = " UPDATE `user` SET `totalMoney`='" + money + "' WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "' ";
                int resultSetMN = statementMN.executeUpdate(sqlMN);
                System.out.println("dongbo thanh cong");
            } catch (Exception e) {
            }
        }
    }

    @Override
    public int dePosit(int money) throws RemoteException {
        int moneyBank = bank.getTotalMoneyInBank();
        int sum = money + totalMoneyInDatabaseUser();
        try {
            Statement statement = Database.databaseMB().createStatement();
            String sql = "SELECT * FROM user WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Statement statementSum = Database.databaseMB().createStatement();
                String sqlSum = " UPDATE `user` SET `totalMoney`='" + sum + "' WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "' ";
                int resultSetSum = statementSum.executeUpdate(sqlSum);
                bank.setTotalMoneyInBank(moneyBank + money);
                user.setTotalMoney(sum);
                
                System.out.println("Nap tien thanh cong");
            }
        } catch (Exception e) {
        }
        return sum;
    }

    @Override
    public void changePassword(String newPassword, String inPutPasswordOld) throws RemoteException {
        String PasswordOld = user.getPassword();
        if (inPutPasswordOld.equals(PasswordOld)) {
            try {
                Statement statementSum = Database.databaseMB().createStatement();
                String sqlSum = " UPDATE `user` SET `password`='" + newPassword + "' WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "' ";
                int resultSetSum = statementSum.executeUpdate(sqlSum);
                user.setPassword(newPassword);
            } catch (SQLException e) {

            }
        }
    }

    @Override
    public void dongBoPassword() throws RemoteException {
        String newPassword = user.getPassword();
        System.out.println(newPassword);
        try {
            // dong bo password mien trung
            Statement statementMT = Database.databaseMT().createStatement();
            String sqlMT = " UPDATE `user` SET `password`='" + newPassword + "' WHERE email = '" + user.getEmail() + "'";
            int resultSetMT = statementMT.executeUpdate(sqlMT);
            // dong bo password mien nam
            Statement statementMN = Database.databaseMN().createStatement();
            String sqlMN = " UPDATE `user` SET `password`='" + newPassword + "' WHERE email = '" + user.getEmail() + "'";
            int resultSetMN = statementMN.executeUpdate(sqlMN);
            System.out.println("dong bo pass complete");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
