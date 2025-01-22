package com.paparazziteam.securityapplicationapp.data.repository

import com.paparazziteam.securityapplicationapp.data.remote.RemoteDataSourcePokemon
import com.paparazziteam.securityapplicationapp.framework.performNetworkFlow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSourcePokemon: RemoteDataSourcePokemon
) {

    suspend fun getPokemonInfo(name: String) = performNetworkFlow {
        remoteDataSourcePokemon.getPokemonInfo(name)
    }

    suspend fun getPokemonInfoSsl(name: String) = performNetworkFlow {
        remoteDataSourcePokemon.getPokemonInfoSsl(name)
    }

    suspend fun getPokemonHttp(name: String) = performNetworkFlow {
        remoteDataSourcePokemon.getPokemonHttp(name)
    }
}