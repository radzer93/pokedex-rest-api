package com.radoslawzerek.pokedexrestapi.exceptions;

/**
 * Author: Radosław Żerek
 */

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
