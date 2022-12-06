/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rmi.model;

import java.io.Serializable;

/**
 *
 * @author TRUNG DUNG
 */
public class Bank implements Serializable{
    private int totalMoneyInBank = 1000000;

    public Bank() {
    }

    public Bank(int totalMoneyInBank) {
        this.totalMoneyInBank = totalMoneyInBank;
    }

    public int getTotalMoneyInBank() {
        return totalMoneyInBank;
    }

    public void setTotalMoneyInBank(int totalMoneyInBank) {
        this.totalMoneyInBank = totalMoneyInBank;
    }
}
