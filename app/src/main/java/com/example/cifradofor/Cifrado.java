package com.example.cifradofor;

import java.util.Locale;
import java.util.SplittableRandom;

public class Cifrado {

    private final String alfabeto = "abcdefghijklmnñopqrstuvwxyz";

    public String cifrar(String texto, int numero) {
        final StringBuilder cifrado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            final boolean upper = Character.isUpperCase(c); // Comprobamos si es mayúscula o no y guardamos su valor
            c = Character.toLowerCase(c); // Hacemos al char (letra) minúscula (aunque sea minúscula)
            final int index = this.alfabeto.indexOf(c); // Comprobamos la posición de la letra en el alfabeto
            char newLetter;

            if (index + numero >= this.alfabeto.length()) {
                final int offSet = -this.alfabeto.length() + (index + numero); // Invertido para que el resultado nos de positivo siempre :D
                newLetter = this.alfabeto.charAt(offSet);
            } else {
                newLetter = this.alfabeto.charAt(index + numero);
            }

            cifrado.append(upper ? Character.toUpperCase(newLetter) : newLetter); // Si era mayuscula transformamos la nueva letra a mayuscula (?), si no lo era, la dejamos igual (:)
            // Este métido anterior se puede hacer tambien como:
            // if (upper) {
            //  cifrado.append(Character.toUpperCase(newLetter));
            // } else {
            //  cifrado.append(newLetter);
            // }
        }

        return cifrado.toString();
    }


    public String descifrar(String texto, int numero) {
        final StringBuilder cifrado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            final boolean upper = Character.isUpperCase(c);
            c = Character.toLowerCase(c);
            final int index = this.alfabeto.indexOf(c);
            char newLetter;

            if (index - numero < 0) {
                final int offSet = this.alfabeto.length() + (index - numero);
                newLetter = this.alfabeto.charAt(offSet);
            } else {
                newLetter = this.alfabeto.charAt(index - numero);
            }

            cifrado.append(upper ? Character.toUpperCase(newLetter) : newLetter);
        }

        return cifrado.toString();
    }
}
