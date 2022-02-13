package com.zywczas.myworkout.fragments.welcome.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zywczas.myworkout.theme.AppTheme

//class WelcomeFragment @Inject constructor(viewModelFactory: UniversalViewModelFactory) : Fragment() {
//
//    private val viewModel: WelcomeViewModel by viewModels { viewModelFactory }
//    private var binding: FragmentWelcomeBinding by autoRelease()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
//        binding.composeView.apply {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent { WelcomeFragmentScreen(findNavController(), viewModel) }
//        }
//        return binding.root
//    }
//
//
//}

@Composable
fun WelcomeFragmentScreen(
//    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
//    val controller = rememberNavController()
    AppTheme {
        Column {
            Text(text = "welcome fragment")
//            Button(onClick = { navController?.navigate(WelcomeFragmentDirections.toWeeksListFragment()) }) {
//                Text(text = "idz do drugiego fragmentu")
//            }
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
//    WelcomeFragmentScreen(WelcomeViewModel())
}

