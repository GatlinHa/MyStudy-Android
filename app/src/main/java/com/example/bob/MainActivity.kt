package com.example.bob

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bob.ui.theme.MyApplication01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation(SampleData.conversationSample)
        }
    }
}

data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 1.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isExpanded) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
    )
}

@Preview
@Composable
fun PreviewConversation() {
    MyApplication01Theme {
        Conversation(SampleData.conversationSample)
    }
}

// SampleData.conversationSample
object SampleData {
    val conversationSample = listOf(
        Message("Colleague", "Test..."),
        Message("Colleague", "List of messages"),
        Message("Colleague", "=============================这是一个长句子==============================="),
        Message("Colleague", "Hi"),
        Message("Colleague", "Test..."),
        Message("Colleague", "List of messages"),
        Message("Colleague", "===============这是一个展开和收起===========\n" +
                "This is a really long message that should wrap\n" +
                "This is a really long message that should wrap\n" +
                "This is a really long message that should wrap\n" +
                "This is a really long message that should wrap\n" +
                "This is a really long message that should wrap\n" +
                "This is a really long message that should wrap\n" +
                "This is a really long message that should wrap"),
        Message("Colleague", "Hi"),
        Message("Colleague", "Test..."),
        Message("Colleague", "List of messages"),
        Message("Colleague", "This is a really long message that should wrap"),
        Message("Colleague", "Hi"),
        Message("Colleague", "Test..."),
        Message("Colleague", "List of messages"),
        Message("Colleague", "This is a really long message that should wrap"),
        Message("Colleague", "Hi"),
        Message("Colleague", "Test..."),
        Message("Colleague", "List of messages"),
        Message("Colleague", "This is a really long message that should wrap"),
        Message("Colleague", "Hi"),
    )
}