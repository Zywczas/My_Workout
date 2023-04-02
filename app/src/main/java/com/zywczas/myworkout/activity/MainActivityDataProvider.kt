package com.zywczas.myworkout.activity

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zywczas.common.utils.SingleLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityDataProvider @Inject constructor(){

    private val _message = SingleLiveData<Int>()
    val message: LiveData<Int> = _message

    private val _isProgressBarVisible = MutableLiveData<Boolean>()
    //todo add observer in main activity
//    val isProgressBarVisible: LiveData<Boolean> = _isProgressBarVisible

    fun postMessage(@StringRes msg: Int){
        _message.postValue(msg)
    }

    fun showProgressBar(show: Boolean){
        _isProgressBarVisible.postValue(show)
    }
}
