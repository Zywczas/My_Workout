package com.zywczas.myworkout.watch.activities

import androidx.activity.ComponentActivity
import com.zywczas.common.di.factories.UniversalViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : ComponentActivity() {

    @Inject
    protected lateinit var viewModelFactory: UniversalViewModelFactory

}