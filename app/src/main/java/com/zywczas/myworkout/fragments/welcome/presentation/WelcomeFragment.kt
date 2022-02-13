package com.zywczas.myworkout.fragments.welcome.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zywczas.common.di.factories.UniversalViewModelFactory
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.activity.Message
import com.zywczas.myworkout.activity.MessageCard
import com.zywczas.myworkout.databinding.FragmentWelcomeBinding
import com.zywczas.myworkout.theme.AppTheme
import javax.inject.Inject

class WelcomeFragment @Inject constructor(viewModelFactory: UniversalViewModelFactory) : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels { viewModelFactory }
    private var binding: FragmentWelcomeBinding by autoRelease()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { WelcomeFragmentScreen() }
        }
        return binding.root
    }

}

@Composable
private fun WelcomeFragmentScreen() {
    AppTheme {
        Column {
            Text(text = "welcome fragment")
            Button(onClick = {  }) {
                Text(text = "idz do drugiego fragmentu")
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "WelcomeFragmentScreen",
)
@Composable
fun PreviewWelcomeFragmentScreen() {
    WelcomeFragmentScreen()
}

