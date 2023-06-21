package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.framework.BaseDataSource
import javax.inject.Inject

class RemoteDataSourcePokemon @Inject constructor(
    val pokemonService: PokemonService
) : BaseDataSource() {

    suspend fun getPokemonInfo(name: String) = getResult (
        call = { pokemonService.getPokemon(name) },
        forceError = false
    )
}