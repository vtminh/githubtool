package com.example.domain.base

import kotlinx.coroutines.Dispatchers

class UseCaseDispatcherImpl : UseCaseDispatcher {
    override fun getWorkerThread() = Dispatchers.IO
    override fun getResultThread() = Dispatchers.Main
}
