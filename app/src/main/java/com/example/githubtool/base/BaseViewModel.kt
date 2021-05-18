package com.example.githubtool.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseUseCase
import com.example.domain.base.DataResponse

/**
 * @author tuanminh.vu
 */
open class BaseViewModel<T>(context: Application) : AndroidViewModel(context) {

    val baseLiveData = MutableLiveData<DataResponse<T>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable?>()

    /**
     * @param dataSource load data from remote or local
     * @param liveData emitted result
     * @param loadingLiveData emitted loading state
     * @param errorLiveData emitted error if any
     * @param shouldUseCache tell the dataSource to load from local database or not
     * @param extraAction do something in viewModel after data is loaded (update pagination info...)
     */
    fun <S : DataResponse<*>> loadData(
        dataSource: BaseUseCase<S>,
        liveData: MutableLiveData<*>? = baseLiveData,
        loadingLiveData: MutableLiveData<Boolean>? = this.loadingLiveData,
        errorLiveData: MutableLiveData<Throwable?>? = this.errorLiveData,
        shouldUseCache: Boolean = false,
        extraAction: ((S) -> Unit)? = null
    ) {
        dataSource.shouldUseCache = shouldUseCache
        if (!shouldUseCache)
            loadingLiveData?.value = true
        dataSource.execute(viewModelScope, {
            if (it.isFromCache && !it.isSuccess()) {
                loadingLiveData?.value = true
            } else if (it.isSuccess()) {
                loadingLiveData?.value = false
            }
            if (!it.isFromCache && !it.isSuccess()) {
                loadingLiveData?.value = false
                errorLiveData?.value = it.error
            }
            extraAction?.invoke(it)
            liveData?.value = it
        }, {
            loadingLiveData?.value = false
            errorLiveData?.value = it
        }, false)
    }

    fun isDataLoading(): Boolean {
        return loadingLiveData.value == true
    }
}