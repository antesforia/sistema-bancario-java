package com.banco.model;

public class CuentaCorriente extends Cuenta{
    private double limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, double saldo, Cliente cliente, double limiteSobregiro) {
        super(numeroCuenta, "CORRIENTE", saldo, cliente);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public boolean retirar(double monto){
        if(monto > 0 && monto <= saldo + limiteSobregiro){
            saldo -= monto;
            return true;
        }
        return false;
    }

}
