package com.p3g3.magellangpt.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.p3g3.magellangpt.R
import com.p3g3.magellangpt.data.remote.responses.MessageResponse
import com.p3g3.magellangpt.domain.models.message.Message
import com.p3g3.magellangpt.ui.core.Destination
import com.p3g3.magellangpt.ui.core.composables.PreviewContent
import com.p3g3.magellangpt.ui.core.navigate
import kotlinx.coroutines.flow.onEach

private typealias UIState = HomeState
private typealias UIAction = HomeAction

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    //val messageResponse by viewModel.messageResponse.observeAsState()

    val messageResponses by viewModel.messagesResponses.collectAsState()
    val conversations by viewModel.conversations.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is Destination.Menu)
                    navController.navigate(destination = event)
            }
    }
    HomeContent(
        state = state,
        onAction = viewModel::handleAction,
        messageResponses = messageResponses,
        conversations = conversations
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun HomeContent(
    state: UIState = UIState(),
    onAction: (UIAction) -> Unit = { },
    messageResponses: List<MessageResponse>,
    conversations: List<Any>

) = Scaffold(
    modifier = Modifier
        .fillMaxSize()
        .background(Brush.linearGradient(listOf(Color(0xff0d506c), Color(0xff00385d)))),
    topBar = {
        Icon(imageVector = Icons.Default.Menu,
            contentDescription = "menu",
            modifier = Modifier
                .padding(25.dp)
                .clickable { onAction(HomeAction.SelectedMenu()) }
                .size(40.dp))
    },
    bottomBar = {
        Column(
            modifier = Modifier
                .padding(20.dp),
            Arrangement.Bottom
        ) {
            var text by remember { mutableStateOf("") }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = "File"
                    )
                }
                TextField(
                    value = text,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { newText ->
                        text = newText
                    },
                    label = { Text(text = "Saisissez votre prompt") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = {
                        IconButton(onClick = {
                            onAction(
                                HomeAction.SendMessage(
                                    Message(
                                        "gpt3",
                                        text
                                    )
                                )
                            )
                        }) {
                            Icon(Icons.Filled.Send, contentDescription = "", tint = Gray)
                        }
                    },
                )
            }

            //counter message

        }
    }
) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Brush.linearGradient(listOf(Color(0xff0d506c), Color(0xff00385d)))),
    ) {
        /*messageResponse?.let { response ->
            MessageCard(response.content)
        } // Afficher un indicateur de chargement si nécessaire */
        /*LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(messageResponses) { test ->
                if (test.role == "assistant"){
                    MessageCard(
                        Modifier.background(Red)
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(5.dp)), test.content)
                }

            }
        }*/
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(conversations) { item ->
                when (item) {
                    is Message -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            MessageCard(
                                Modifier
                                    .padding(10.dp)
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .background(
                                        Brush.linearGradient(
                                            listOf(
                                                Color(0xff00b5e2),
                                                Color(0xff00637c)
                                            )
                                        )
                                    ),
                                item.requestMessage
                            )
                        }
                    }

                    is MessageResponse -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            MessageCard(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .background(
                                        Brush.linearGradient(
                                            listOf(
                                                Color(0xffacaaaa),
                                                Color(0xff999999)
                                            )
                                        )
                                    ),
                                item.content
                            )
                        }
                    }
                }
            }
        }


    }
}

/*@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()
    // Un état local pour le texte du message
    var messageText by remember { mutableStateOf("") }

    // Observer les messages
    val messages = viewModel.messages.observeAsState()

    // Un exemple d'interface utilisateur
    Column {
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            label = { Text("Message") }
        )
        Button(onClick = {
            viewModel::handleAction // Créez une instance de Message avec le texte
        }) {
            Text("Envoyer")
        }
        messages.value?.let { response ->
           Text(text = response.content)
        }
    }
}*/


@Composable
fun MessageCard(modifier: Modifier, response: String) {
    Box(modifier = modifier.padding(10.dp)) {
        Text(text = response, color = White)
    }
}

@Composable
@Preview
fun MessageCardPreview() = PreviewContent {
    MessageCard(modifier = Modifier.background(Red), response = "Oui")
}
