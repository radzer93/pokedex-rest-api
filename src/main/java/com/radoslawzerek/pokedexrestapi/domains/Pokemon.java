package com.radoslawzerek.pokedexrestapi.domains;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "POKEMONS")
public class Pokemon {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "POKEMON_ID", unique = true)
    private Long pokemonId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DEFENCE")
    private int defence;

    @Column(name = "ATTACK")
    private int attack;

    @Column(name = "TYPE")
    private String type;

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", defence=" + defence +
                ", attack=" + attack +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
