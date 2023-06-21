package com.paparazziteam.securityapplicationapp.framework

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T> performNetworkFlow(networkCall: suspend () -> T): Flow<T> =
    flow {
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }.flowOn(Dispatchers.IO)