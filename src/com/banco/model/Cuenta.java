package com.banco.model;

import java.util.Scanner;

public class Cuenta{

    protected String numeroCuenta;
    protected String tipoCuenta;
    protected double saldo;
    protected Cliente cliente;

    public Cuenta(){
    }

    public Cuenta(String numeroCuenta, String tipoCuenta, double saldo, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public void mostrarCuenta() {
        System.out.println("----------------------");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("DNI: " + cliente.getDni());
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Tipo: " + tipoCuenta);
        System.out.println("Saldo: " + saldo);
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean depositar(double monto){
        if(monto>0) {
            saldo += monto;
            return true;
        } else {
            return false;
        }
    }

    public boolean retirar(double monto){
        if(monto>0 && monto<=saldo){
            saldo -= monto;
            return true;
        } else {
            return false;
        }
    }
}
