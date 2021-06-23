package com.radoslawzerek.pokedexrestapi.dao;

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonPaginationDao extends PagingAndSortingRepository<Pokemon, Integer> {
}
