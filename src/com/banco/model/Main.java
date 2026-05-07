package com.banco.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        iniciarPrograma();
    }

    public static void iniciarPrograma(){

        Cliente cliente1 = new Cliente("Henry", "71742102");
        Cliente cliente2 = new Cliente("Yamile", "77162899");
        Cliente cliente3 = new Cliente("Carlos", "71742103");
        Cuenta cuenta1 = new CuentaCorriente("CC-001", 1200.0, cliente1, 300);
        Cuenta cuenta2 = new CuentaAhorros("CA-001", 1500.0, cliente2, 0.15);
        Cuenta cuenta3 = new CuentaAhorros("CA-002", 1100.0, cliente3, 0.10);

        ArrayList<Cuenta> cuentas = new ArrayList<>();

        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);

        var consola = new Scanner(System.in);
        var salir = false;

        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, cuentas);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        consola.close();

    }

    public static int mostrarMenu(Scanner consola){
        System.out.print("""
                \n==== Sistema Bancario ====
                1. Mostrar todas las cuentas
                2. Depositar
                3. Retirar
                4. Aplicar Interes
                5. Mostrar Cuentas Ahorro
                6. Mostrar Cuentas Corriente
                7. Salir
                Escoge una opción:\s""");
        try{
            return Integer.parseInt(consola.nextLine());
        }catch (NumberFormatException e) {

            System.out.println("Error: Ingrese un número válido.");

            return Integer.MIN_VALUE;


        }
    }

    public static boolean ejecutarOpciones(Scanner consola, int opcion, ArrayList<Cuenta> cuentas){
        if(opcion == Integer.MIN_VALUE){
            return false;
        }

            switch(opcion){
                case 1 -> {
                    mostrarCuentas(cuentas);
                }
                case 2 -> {
                    depositar(consola, cuentas);
                }
                case 3 -> {
                    retirar(consola, cuentas);
                }
                case 4 -> {
                    aplicarInteres(consola, cuentas);
                }
                case 5 -> {
                    mostrarCuentasAhorro(cuentas);
                }
                case 6 -> {
                    mostrarCuentasCorriente(cuentas);
                }
                case 7 -> {
                    System.out.println("Vuelva pronto!");
                    return true;
                }
                default -> {
                    System.out.println("Opción invalida.");
                }
            }
            return false;
    }

    public static void depositar(Scanner consola, ArrayList<Cuenta> cuentas){
        System.out.println("Ingrese el número de cuenta: ");
        String numeroCuenta = consola.nextLine();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if(cuenta == null){
            return;
        }

        System.out.println("Monto a depositar: ");
        try{
            var monto = Double.parseDouble(consola.nextLine());
            var resultado = cuenta.depositar(monto);
            if (resultado){
                System.out.println("Deposito Exitoso!");
                System.out.println("------------------");
                cuenta.mostrarCuenta();
            } else {
                System.out.println("Monto inválido.");
            }
        }catch(NumberFormatException e) {
            System.out.println("Error: Ingrese un monto válido.");
        }
    }

    public static void retirar(Scanner consola, ArrayList<Cuenta> cuentas){
        System.out.println("Ingrese el número de cuenta: ");
        String numeroCuenta = consola.nextLine();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if(cuenta == null){
            return;
        }

        System.out.println("Monto a retirar: ");
        try{
            var monto = Double.parseDouble(consola.nextLine());
            var resultado = cuenta.retirar(monto);
            if (resultado){
                System.out.println("Retiro Exitoso!");
                System.out.println("------------------");
                cuenta.mostrarCuenta();
            } else {
                System.out.println("Monto inválido o saldo insuficiente.");
            }
        }catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    public static void aplicarInteres(Scanner consola, ArrayList<Cuenta> cuentas){
        System.out.println("Ingrese el número de cuenta: ");

        String numeroCuenta = consola.nextLine();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if(cuenta == null){
            System.out.println("Cuenta no encontrada.");
            return;
        }

        if (cuenta instanceof CuentaAhorros cuentaAhorros){
            cuentaAhorros.aplicarInteres();
            System.out.println("Interes aplicado correctamente.");
            System.out.println("Saldo actual: " + cuentaAhorros.getSaldo());
        }else{
            System.out.println("Esta cuenta no genera intereses");
        }
    }

    public static Cuenta buscarCuenta(ArrayList<Cuenta> cuentas, String numeroCuenta){
        for(Cuenta cuenta : cuentas){
            if(cuenta.getNumeroCuenta().equals(numeroCuenta)){
                return cuenta;
            }
        }
        System.out.println("No se encontró la cuenta.");
        return null;
    }

    public static void mostrarCuentas(ArrayList<Cuenta> cuentas){
        for(Cuenta cuenta : cuentas){
            System.out.println("---------------------------");
            cuenta.mostrarCuenta();
        }
    }

    public static void mostrarCuentasAhorro(ArrayList<Cuenta> cuentas){
        cuentas.stream().filter(cuenta -> cuenta instanceof CuentaAhorros)
                .forEach(Cuenta::mostrarCuenta);
    }

    public static void mostrarCuentasCorriente(ArrayList<Cuenta> cuentas){
        cuentas.stream().filter(cuenta -> cuenta instanceof CuentaCorriente)
                .forEach(Cuenta::mostrarCuenta);
    }

}
