package com.radoslawzerek.pokedexrestapi;

import com.radoslawzerek.pokedexrestapi.dao.PokemonDao;
import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PokemonControllerTestSuite {

    @Autowired
    private PokemonDao pokemonDao;

    /*'testGetAllPokemons' and 'testDeletePokemon' do not pass after rerunning the tests,
    the reason being that 'testGetAllPokemons' leaves objects in DB.
    Attempts to fix this by annotating '@Transactional' or adding a 'CleanUp' section to the test failed.
    '@BeforeEach' solved the problem.*/

    @BeforeEach
    public void beforeEachTest() {
        pokemonDao.deleteAll();
    }

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
        Pokemon pokemon = new Pokemon("Pikachu", "Electric");

        //When
        pokemonDao.save(pokemon);
        Long pokemonId = pokemon.getPokemonId();
        String name = pokemon.getName();
        String type = pokemon.getType();

        //Then
        List<Pokemon> pokemonList = pokemonDao.findAll();
        assertEquals(pokemonId, pokemonList.get(0).getPokemonId());
        assertEquals(name, pokemonList.get(0).getName());
        assertEquals(type, pokemonList.get(0).getType());
    }

    @Test
    public void testGetAllPokemons() {
        //Given
        Pokemon pokemon0 = new Pokemon("Charmander");
        Pokemon pokemon1 = new Pokemon("Squirtle");
        Pokemon pokemon2 = new Pokemon("Bulbasaur");

        //When
        pokemonDao.save(pokemon0);
        pokemonDao.save(pokemon1);
        pokemonDao.save(pokemon2);

        //Then
        List<Pokemon> pokemonList = pokemonDao.findAll();
        assertEquals(3, pokemonList.size());
    }

    @Test
    public void testDeletePokemon() {
        //Given
        Pokemon pokemon = new Pokemon("Pikachu");

        //When
        pokemonDao.save(pokemon);
        pokemonDao.deleteById(pokemon.getPokemonId());

        //Then
        List<Pokemon> pokemonList = pokemonDao.findAll();
        assertEquals(0, pokemonList.size());
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
