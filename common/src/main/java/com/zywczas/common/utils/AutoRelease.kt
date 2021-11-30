package com.zywczas.common.utils

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : Any> LifecycleOwner.autoRelease() =
    AutoReleasedProperty<T>(this)

class AutoReleasedProperty<T : Any>(lifecycleOwner: LifecycleOwner) :
    ReadWriteProperty<Any, T> {

    private var internalValue: T? = null
    private var ownerDestroyed = false

    init {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) //todo deprecated
            fun onDestroy() {
                ownerDestroyed = true
                internalValue = null
            }
        })
    }

    //todo moze tak to zrobic:
//    fun collect(lifecycleOwner: LifecycleOwner, fragmentManager: FragmentManager, context: Context) {
//        lifecycleOwner.lifecycleScope.launch {
//            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                attachmentsManager.attachmentsViewModel.cachedAttachments.collect {
//                    dismissProgress(fragmentManager)
//                    onCacheComplete(context, it)
//                }
//            }
//        }
//    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        internalValue ?: run {
            @Suppress("ReplaceGuardClauseWithFunctionCall")
            if (ownerDestroyed){
                throw IllegalStateException(
                    "Property '$property.name' is unavailable because its owning '${thisRef.javaClass.name} has been destroyed."
                )
            } else {
                throw IllegalStateException(
                    "Property '$property.name' cannot be accessed because it has not yet been set. " +
                            "This '${AutoReleasedProperty::class.java.simpleName}' is effectively a 'lateinit var'.")
            }
        }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        internalValue = value
    }

}