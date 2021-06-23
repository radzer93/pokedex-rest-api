package com.radoslawzerek.pokedexrestapi.controllers;

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import com.radoslawzerek.pokedexrestapi.dto.PokemonDto;
import com.radoslawzerek.pokedexrestapi.exceptions.PokemonNotFoundException;
import com.radoslawzerek.pokedexrestapi.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/getAllPokemons")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonDto> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @GetMapping("/getPokemon/{pokemonId}")
    @ResponseStatus(HttpStatus.OK)
    public PokemonDto getPokemon(@PathVariable Long pokemonId) throws PokemonNotFoundException {
        return pokemonService.getPokemon(pokemonId);
    }

    @GetMapping("/findPokemonByName")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Pokemon> findByName(@RequestParam String pokemonName) throws PokemonNotFoundException {
        return pokemonService.findPokemonByName(pokemonName);
    }

    @GetMapping("/findPokemonByType")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Pokemon> findByType(@RequestParam String pokemonType) throws PokemonNotFoundException {
        return pokemonService.findPokemonByType(pokemonType);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPokemon(@RequestBody PokemonDto pokemonDto) {
        pokemonService.addPokemon(pokemonDto);
    }

    @DeleteMapping("/{pokemonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable Long pokemonId) {
        pokemonService.deleteById(pokemonId);
    }

    @PutMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PokemonDto updatePokemon(@PathVariable Long userId,
                                    @RequestBody PokemonDto pokemonDto) throws PokemonNotFoundException {
        return pokemonService.updatePokemon(userId, pokemonDto);
    }
}
