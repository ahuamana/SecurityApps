package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.framework.BaseDataSource
import javax.inject.Inject

class RemoteDataSourcePokemon @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonServiceSsl: PokemonServiceSsl,
    private val httpGeneralService: HttpGeneralService
) : BaseDataSource() {

    suspend fun getPokemonInfo(name: String) = getResult (
        call = { pokemonService.getPokemon(name) },
        forceError = false
    )

    suspend fun getPokemonInfoSsl(name: String) = getResult (
        call = { pokemonServiceSsl.getPokemon(name) },
        forceError = false
    )

    suspend fun getPokemonHttp(name: String) = getResult (
        call = { httpGeneralService.getPokemon(name) },
        forceError = false
    )

}