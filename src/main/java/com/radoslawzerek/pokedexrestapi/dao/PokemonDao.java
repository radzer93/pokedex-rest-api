package com.radoslawzerek.pokedexrestapi.dao;

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon, Long> {
}
