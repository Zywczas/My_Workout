package com.zywczas.common.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { genericItem ->
            genericItem?.let {
                observer.onChanged(genericItem)
                postValue(null)
            }
        }
    }

}