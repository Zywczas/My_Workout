package com.zywczas.myworkout.fragments.weekslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.zywczas.myworkout.theme.AppTheme
import javax.inject.Inject

class WeeksListFragment @Inject constructor(): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent { WeeksListFragmentScreen() }
        }
    }

}

@Composable
private fun WeeksListFragmentScreen() {
    AppTheme {
        Column {
            Text(text = "WeeksListFragment")
        }
    }
}