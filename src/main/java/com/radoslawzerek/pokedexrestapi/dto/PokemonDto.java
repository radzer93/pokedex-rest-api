package com.radoslawzerek.pokedexrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PokemonDto {

    private Long pokemonId;
    private String name;
    private int attack;
    private int defence;
    private String type;
}