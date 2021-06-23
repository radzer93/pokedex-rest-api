package com.radoslawzerek.pokedexrestapi.services;

import com.radoslawzerek.pokedexrestapi.dao.PokemonDao;
import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import com.radoslawzerek.pokedexrestapi.dto.PokemonDto;
import com.radoslawzerek.pokedexrestapi.exceptions.PokemonNotFoundException;
import com.radoslawzerek.pokedexrestapi.mappers.PokemonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private PokemonMapper pokemonMapper;

    public List<PokemonDto> getAllPokemons() {
        List<Pokemon> pokemonList = pokemonDao.findAll();
        return pokemonMapper.mapToPokemonDtoList(pokemonList);
    }

    public PokemonDto getPokemon(Long id) throws PokemonNotFoundException {
        return pokemonDao.findById(id).map(pokemonMapper::mapToPokemonDto)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with id " + id + " not found"));
    }

    public void addPokemon(PokemonDto pokemonDto) {
        pokemonDao.save(pokemonMapper.mapToPokemon(pokemonDto));
    }

    /*public Optional<Pokemon> findPokemonById (final Long id) {
        return pokemonDao.findById(id);
    }*/

    public Optional<Pokemon> findPokemonByName(final String name) {
        return pokemonDao.findByName(name);
    }

    public Optional<Pokemon> findPokemonByType(final String type) {
        return pokemonDao.findByType(type);
    }

    public PokemonDto updatePokemon(Long id, PokemonDto pokemonDto) throws PokemonNotFoundException {
        return pokemonDao.findById(id).map(pokemon -> {
            Pokemon pokemonUpdated = pokemonMapper.mapToPokemon(pokemonDto);
            pokemonDao.save(pokemonUpdated);
            return pokemonMapper.mapToPokemonDto(pokemonUpdated);}
        ).orElseThrow(() -> new PokemonNotFoundException("User with id " + id + " not found"));
    }

    public void deleteById (final Long id) {
        pokemonDao.deleteById(id);
    }
}
