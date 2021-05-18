package com.example.domain.user

import com.example.domain.base.BaseUseCase
import com.example.domain.base.DataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetFollowerListUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseUseCase<DataResponse<List<User>>>() {

    lateinit var userName:String
    override suspend fun run(onComplete: (DataResponse<List<User>>) -> Unit, coroutineScope: CoroutineScope) {
        val remote = repository.getFollowerListFromRemote(userName)
        coroutineScope.launch(dispatcher.getResultThread()) { onComplete.invoke(remote) }
    }
}