package com.example.mn_garg_04.util;

// Esta clase solo contiene las operaciones matemáticas
public class Basicas {

    // Suma dos números y devuelve el resultado
    public static double sumar(double a, double b) {
        return a + b;
    }

    // Resta el segundo número al primero
    public static double restar(double a, double b) {
        return a - b;
    }

    // Multiplica ambos números
    public static double multiplicar(double a, double b) {
        return a * b;
    }

    // Divide el primer número entre el segundo
    public static double dividir(double a, double b) {
        // Si por alguna razón intentamos dividir por cero aquí, marcamos un error interno
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return a / b;
    }
}
