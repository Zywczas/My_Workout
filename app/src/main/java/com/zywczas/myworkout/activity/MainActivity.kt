package com.zywczas.myworkout.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.myworkout.navigation.NavHostMain
import com.zywczas.myworkout.composeitems.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainActivityScreen()
            }
        }
    }

}

@Composable
private fun MainActivityScreen() {
    NavHostMain()
}

//todo do usuniecia
//@Composable
//private fun Conversation(messages: List<Message>) {
//    LazyColumn {
//        items(messages) { message ->
//            MessageCard(message)
//        }
//    }
//}

//@Composable
//fun MessageCard(msg: Message) {
//    Row(modifier = Modifier.padding(8.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.profile_picture),
//            contentDescription = "Zdjęcie profilowe",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(3.dp, MaterialTheme.colors.secondary, CircleShape)
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//
//        var isExpanded by remember { mutableStateOf(false) }
//        val surfaceColor: Color by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
//
//        Column(
//            modifier = Modifier
//                .background(color = Color.DarkGray)
//                .clickable { isExpanded = isExpanded.not() }
//        ) {
//            Text(
//                text = msg.author,
//                color = MaterialTheme.colors.secondaryVariant
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            Surface(
//                shape = MaterialTheme.shapes.medium,
//                elevation = 3.dp,
//                color = surfaceColor,
//                modifier = Modifier
//                    .animateContentSize()
//                    .padding(1.dp)
//            ) {
//                Text(
//                    text = msg.body,
//                    style = MaterialTheme.typography.body2,
//                    modifier = Modifier.padding(10.dp),
//                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
//                )
//            }
//        }
//    }
//}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "MainActivityScreen DayMode",
    showSystemUi = true
)
@Composable
fun PreviewMessageCard() {
    MainActivityScreen()
}