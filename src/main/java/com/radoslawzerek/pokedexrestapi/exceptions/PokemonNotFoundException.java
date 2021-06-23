package com.radoslawzerek.pokedexrestapi.exceptions;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
