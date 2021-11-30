package com.zywczas.myworkout.watch.fragments.trainingplan.weekslist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.databinding.FragmentWeeksListBinding
import com.zywczas.myworkout.watch.databinding.FragmentWelcomeBinding
import javax.inject.Inject

class WeeksListFragment
@Inject constructor() : Fragment() {

    private var binding: FragmentWeeksListBinding by autoRelease()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWeeksListBinding.inflate(inflater, container, false)
        return binding.root
    }

}