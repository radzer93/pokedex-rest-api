package com.radoslawzerek.pokedexrestapi.dao;

/**
 * Author: Radosław Żerek
 */

import com.radoslawzerek.pokedexrestapi.domains.Pokemon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonPaginationDao extends PagingAndSortingRepository<Pokemon, Long> {
}
