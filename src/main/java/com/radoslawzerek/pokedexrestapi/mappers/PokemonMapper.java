package com.radoslawzerek.pokedexrestapi.mappers;

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import com.radoslawzerek.pokedexrestapi.domains.PokemonDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonMapper {

    public Pokemon mapToPokemon(final PokemonDto pokemonDto) {
        return new Pokemon(pokemonDto.getPokemonId(),
                pokemonDto.getAttack(),
                pokemonDto.getDefence(),
                pokemonDto.getName(),
                pokemonDto.getType()
        );
    }

    public PokemonDto mapToPokemonDto(final Pokemon pokemon) {
        return new PokemonDto(pokemon.getPokemonId(),
                pokemon.getAttack(),
                pokemon.getDefence(),
                pokemon.getName(),
                pokemon.getType()
        );
    }

    public List<PokemonDto> mapToPokemonDtoList(final List<Pokemon> pokemonList) {
        return pokemonList.stream().map(this::mapToPokemonDto).collect(Collectors.toList());
    }
}
