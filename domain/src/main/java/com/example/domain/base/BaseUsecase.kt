package com.example.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author tuanminh.vu
 */
abstract class BaseUseCase<T>(var dispatcher: UseCaseDispatcher = UseCaseDispatcherImpl()) {

    var shouldUseCache = false

    abstract suspend fun run(onComplete: (T) -> Unit, coroutineScope: CoroutineScope)

    var lastJob: Deferred<Any>? = null

    fun execute(coroutineScope: CoroutineScope, onComplete: (T) -> Unit, onError: (Throwable) -> Unit) {
        execute(coroutineScope, onComplete, onError, true)
    }

    fun execute(coroutineScope: CoroutineScope, onComplete: (T) -> Unit, onError: (Throwable) -> Unit, cancelPrevious: Boolean) {
        if (cancelPrevious) lastJob?.cancel()
        lastJob = coroutineScope.async(dispatcher.getWorkerThread()) {
            try {
                this@BaseUseCase.run(onComplete, coroutineScope)
            } catch (e: Exception) {
                coroutineScope.launch(dispatcher.getResultThread()) { onError.invoke(e) }
            }
        }
    }

    fun cancel() = lastJob?.cancel()

}