package com.zywczas.myworkout.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
            MainActivityCompose()
        }
    }

}

data class Message(val author: String, val body: String)

private val przykladowaLista = listOf(
    Message("Piotr1", "niezle1"),
    Message("Piotr2", "niezle2"),
    Message("Piotr3", "niezle3"),
    Message("Piotr4", "niezle4"),
    Message("Piotr5", "niezle5"),
    Message("Piotr6", "niezle6"),
    Message("Piotr7", "niezle7"),
    Message("Piotr8", "niezle8"),
    Message("Piotr9", "niezle9"),
    Message("Piotr10", "niezle10"),
    Message("Piotr11", "niezle9"),
    Message("Piotr12", "niezle9"),
    Message("Piotr13", "niezle9"),
)

@Composable
private fun MainActivityCompose(){
    AppTheme {
        Conversation(przykladowaLista)
    }
}

@Composable
private fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages) { message ->  
            MessageCard(message)
        }
    }
}

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
        Column(modifier = Modifier.background(color = Color.DarkGray)) {
            Text(text = msg.author,
                color = MaterialTheme.colors.secondaryVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 3.dp) {
                Text(text = msg.body,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "MainActivityCompose",
    showSystemUi = true
)
@Composable
fun PreviewMessageCard(){
    MainActivityCompose()
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "MessageCard",
)
@Composable
fun Preview2MessageCard(){
    AppTheme {
        MessageCard(Message("Maciej", "ładne body"))
    }
}