package com.radoslawzerek.pokedexrestapi.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PokemonDto {

    private Long pokemonId;
    private int attack;
    private int defence;
    private String name;
    private String type;
}