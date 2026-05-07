package com.banco.service;

import com.banco.model.Cuenta;
import com.banco.model.CuentaAhorros;

import java.util.ArrayList;
import java.util.Scanner;

public class CuentaService {

    public Cuenta buscarCuenta(ArrayList<Cuenta> cuentas, String numeroCuenta){
        for(Cuenta cuenta : cuentas){
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)){
                return cuenta;
            }
        }
        System.out.println("No se encontró la cuenta");
        return null;
    }

    public void retirar(Scanner consola, ArrayList<Cuenta> cuentas){
        System.out.print("Ingrese el número de cuenta: ");
        var numeroCuenta = consola.nextLine();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if (cuenta == null){
            return;
        }

        System.out.print("Ingrese el monto a retirar: ");
        try{
            var monto = Double.parseDouble(consola.nextLine());
            var resultado = cuenta.retirar(monto);
            if (resultado){
                System.out.println("Retiro exitoso.");
                cuenta.mostrarCuenta();
            }else{
                System.out.println("Monto invalido o saldo insuficiente.");
            }
        }catch (NumberFormatException e){
            System.out.println("Error: Ingrese un número válido.");
        }
    }

    public void depositar (ArrayList<Cuenta> cuentas, Scanner consola){
        System.out.print("Ingrese el número de cuenta: ");
        var numeroCuenta = consola.nextLine();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);
        if (cuenta == null){
            return;
        }

        System.out.print("Ingrese el monto a depositar: ");
        try{
            var monto = Double.parseDouble(consola.nextLine());
            var resultado = cuenta.retirar(monto);
            if (resultado){
                System.out.println("Deposito exitoso!");
                cuenta.mostrarCuenta();
            } else {
                System.out.println("Monto invalido");
            }
        }catch (NumberFormatException e){
            System.out.println("Error: Ingrese un número válido.");
        }
    }

    public void aplicarInteres(ArrayList<Cuenta> cuentas, Scanner consola){
        System.out.println("Ingrese el número de cuenta");
        var numeroCuenta = consola.nextLine();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if (cuenta == null){
            System.out.println("No se encontró la cuenta");
            return;
        }

        if (cuenta instanceof CuentaAhorros cuentaAhorros){
            cuentaAhorros.aplicarInteres();
            System.out.println("Interes aplicado correctamente.");
            System.out.println("Saldo actual: " + cuentaAhorros.getSaldo());
        }
    }

    public void mostrarCuentasSaldoMayor(ArrayList<Cuenta> cuentas, Scanner consola){
        System.out.print("Ingrese el monto minimo: ");
        try{
            var monto = Double.parseDouble(consola.nextLine());
            cuentas.stream().filter(cuenta -> cuenta.getSaldo() > monto)
                    .forEach(Cuenta::mostrarCuenta);
        }catch (NumberFormatException e){
            System.out.println("Error: Ingrese un monto válido.");
        }

    }

}
