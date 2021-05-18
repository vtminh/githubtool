package com.example.domain.base

import kotlin.coroutines.CoroutineContext

interface UseCaseDispatcher {
    fun getWorkerThread(): CoroutineContext
    fun getResultThread(): CoroutineContext
}
