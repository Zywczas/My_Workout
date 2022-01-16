package com.zywczas.myworkout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import com.zywczas.common.di.factories.UniversalFragmentFactory
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var fragmentFactory: UniversalFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent { 
            Text(text = "Compose dziala!")
        }
    }

}