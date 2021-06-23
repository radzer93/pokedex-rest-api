package com.radoslawzerek.pokedexrestapi.mappers;

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import com.radoslawzerek.pokedexrestapi.dto.PokemonDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonMapper {

    public Pokemon mapToPokemon(final PokemonDto pokemonDto) {
        return new Pokemon(pokemonDto.getPokemonId(),
                pokemonDto.getName(),
                pokemonDto.getAttack(),
                pokemonDto.getDefence(),
                pokemonDto.getType()
        );
    }

    public PokemonDto mapToPokemonDto(final Pokemon pokemon) {
        return new PokemonDto(pokemon.getPokemonId(),
                pokemon.getName(),
                pokemon.getAttack(),
                pokemon.getDefence(),
                pokemon.getType()
        );
    }

    public List<PokemonDto> mapToPokemonDtoList(final List<Pokemon> pokemonList) {
        return pokemonList.stream().map(this::mapToPokemonDto).collect(Collectors.toList());
    }
}
