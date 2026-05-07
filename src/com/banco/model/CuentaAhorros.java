package com.banco.model;

public class CuentaAhorros extends Cuenta{
    private double interes;

    public CuentaAhorros(String numeroCuenta, double saldo, Cliente cliente, double interes) {
        super(numeroCuenta, "AHORRO", saldo, cliente);
        this.interes = interes;
    }

    public void aplicarInteres(){
        saldo += saldo * interes;
    }
}
