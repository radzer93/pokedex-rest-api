package com.radoslawzerek.pokedexrestapi.dao;

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon, Long> {

    Optional<Pokemon> findByName(String name);

    Optional<Pokemon> findByType(String type);
}
