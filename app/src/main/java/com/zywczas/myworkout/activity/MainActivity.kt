package com.zywczas.myworkout.activity

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.myworkout.navigation.NavHostMain
import com.zywczas.myworkout.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataProvider: MainActivityDataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                NavHostMain()
            }
        }
        setupObservers()
    }

    private fun setupObservers() {
        dataProvider.message.observe(this) {
            //todo change to snackbar in composable
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "MainActivityScreen DayMode",
    showSystemUi = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "MainActivityScreen NightMode",
    showSystemUi = true
)
@Composable
private fun Preview() {
    NavHostMain()
}
