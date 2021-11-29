package com.zywczas.myworkout.watch.fragments.welcome.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.FragmentWelcomeBinding
import javax.inject.Inject

class WelcomeFragment @Inject constructor() : Fragment() {

    private var binding: FragmentWelcomeBinding by autoRelease()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

}