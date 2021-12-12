package com.zywczas.myworkout.watch.activities

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zywczas.common.utils.SingleLiveData

abstract class BaseViewModel : ViewModel(){

    private val _message = SingleLiveData<@StringRes Int>()
    val message: LiveData<Int> = _message

    fun postMessage(@StringRes msg: Int){
        _message.postValue(msg)
    }

}