package com.zywczas.myworkout.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zywczas.myworkout.R
import com.zywczas.myworkout.fragments.welcome.presentation.WelcomeViewModel
import com.zywczas.myworkout.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }

}

data class Message(val author: String, val body: String)

private val przykladowaLista = listOf(
    Message("Piotr1", "niezle1\nniezle1\nniezle1\nniezle1\nniezle1\nniezle1\nniezle1\n"),
    Message("Piotr2", "niezle2\nniezle1\nniezle1\nniezle1\nniezle1\n"),
    Message("Piotr3", "niezle3\nniezle1"),
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
private fun MainActivityScreen(
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    AppTheme {
        Conversation(przykladowaLista)
    }
}

@Composable
private fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Zdjęcie profilowe",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)

        Column(
            modifier = Modifier
                .background(color = Color.DarkGray)
                .clickable { isExpanded = isExpanded.not() }
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 3.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(10.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
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
fun PreviewMessageCard() {
    MainActivityScreen()
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "MessageCard",
)
@Composable
fun Preview2MessageCard() {
    AppTheme {
        MessageCard(Message("Maciej", "ładne body"))
    }
}