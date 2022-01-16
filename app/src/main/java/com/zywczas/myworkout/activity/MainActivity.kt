package com.zywczas.myworkout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.myworkout.R

class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var fragmentFactory: UniversalFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Piotr", "ładne body"))
        }
    }

}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row() {
        Image(painter = painterResource(id = R.drawable.profile_picture), contentDescription = "Zdjęcie profilowe")
        Column() {
            Text(text = msg.author)
            Text(text = msg.body)

        }
    }

}

@Preview
@Composable
fun PreviewMessageCard(){
    MessageCard(Message("Piotr", "ładne body"))
}