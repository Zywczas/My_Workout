package com.zywczas.myworkout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zywczas.myworkout.R
import com.zywczas.myworkout.theme.AppTheme

class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var fragmentFactory: UniversalFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MessageCard(Message("Piotr", "ładne body"))
            }
        }
    }

}

val LightColors = lightColors(
    primary = Color.Magenta,
    secondary = Color.Red,
    secondaryVariant = Color.Cyan
)

val DarkColors = darkColors(
    primary = Color.DarkGray,
    secondaryVariant = Color.Blue
)

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Zdjęcie profilowe",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(text = msg.author,
                color = MaterialTheme.colors.secondaryVariant
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = msg.body)

        }
    }

}

@Preview
@Composable
fun PreviewMessageCard(){
    AppTheme {
        MessageCard(Message("Piotr", "ładne body"))
    }
}