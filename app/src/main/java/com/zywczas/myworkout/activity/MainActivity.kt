package com.zywczas.myworkout.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zywczas.common.di.factories.UniversalFragmentFactory
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: UniversalFragmentFactory

    private var binding: ActivityMainBinding by autoRelease()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}