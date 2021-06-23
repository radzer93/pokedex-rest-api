package com.radoslawzerek.pokedexrestapi;

import com.radoslawzerek.pokedexrestapi.dao.PokemonDao;
import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PokemonControllerTestSuite {

    @Autowired
    private PokemonDao pokemonDao;

    @Test
    public void testSavePokemon() {
        //Given
        Pokemon pokemon = new Pokemon("Charmander");

        //When
        pokemonDao.save(pokemon);

        //Then
        Optional<Pokemon> pokemonOptional = pokemonDao.findById(pokemon.getPokemonId());
        assertTrue(pokemonOptional.isPresent());
    }

    @Test
    public void testFindPokemon() {
        //Given
        Pokemon pokemon = new Pokemon(1L, "Pikachu", 4, 5, "Electric");

        //When
        pokemonDao.save(pokemon);
        Long pokemonId = pokemon.getPokemonId();
        String name = pokemon.getName();
        String type = pokemon.getType();

        //Then
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemon);

        assertEquals(pokemonId, pokemons.get(0).getPokemonId());
        assertEquals(name, pokemons.get(0).getName());
        assertEquals(type, pokemons.get(0).getType());
    }

    @Test
    public void testDeletePokemon() {
        //Given
        Pokemon pokemon = new Pokemon("Pikachu");

        //When
        pokemonDao.save(pokemon);
        pokemonDao.deleteById(pokemon.getPokemonId());

        //Then
        assertFalse(pokemonDao.existsById(pokemon.getPokemonId()));
    }

    @Test
    public void testUpdatePokemon() {
        //Given
        Pokemon pokemon = new Pokemon("Pikachu");

        //When
        pokemonDao.save(pokemon);
        Long pokemonId = pokemon.getPokemonId();
        Pokemon updatePokemon = pokemonDao.getById(pokemonId);
        updatePokemon.setName("Charmander");
        pokemonDao.save(updatePokemon);
        Long updatePokemonId = updatePokemon.getPokemonId();

        //Then
        assertEquals(pokemonId, updatePokemonId);
        assertEquals("Charmander", updatePokemon.getName());
    }
}
