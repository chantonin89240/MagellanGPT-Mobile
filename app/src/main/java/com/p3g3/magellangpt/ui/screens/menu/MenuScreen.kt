package com.p3g3.magellangpt.ui.screens.menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.p3g3.magellangpt.ui.core.composables.PreviewContent

private typealias UIState = MenuState

@Composable
fun MenuScreen(
    navController: NavController,
) {
    val viewModel: MenuViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    MenuContent(
        state = state,
        onClickBack = navController::popBackStack
    )

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun MenuContent(
    state: UIState = UIState(),
    onClickBack: () -> Unit = { }
) = Scaffold(
    modifier = Modifier.background(Brush.linearGradient(listOf(Color(0xff0d506c), Color(0xff00385d)))),
    topBar = {
    Row(
        modifier = Modifier
            .background(Brush.linearGradient(listOf(Color(0xff0d506c), Color(0xff00385d))))
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onClickBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(40.dp))
        }
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Prénom / Nom",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .clickable(onClick = onClickBack),
                tint = Color.White,
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account")
        }

    }
}) { paddingValues ->
    Box(
        modifier = Modifier
            .background(Brush.linearGradient(listOf(Color(0xff0d506c), Color(0xff00385d))))
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row {
            Text(
                text = "Bonjour",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Steeve GUILLOT",
                color = Color.Cyan,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Column(modifier = Modifier
        ) {
            var text by remember { mutableStateOf(TextFieldValue("")) }
            Row(

            ) {
                TextField(
                    value = text,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent),
                    onValueChange = {
                        text = it
                    },
                    label = { Text(text = "Nouveau chat")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = {
                        Icon(Icons.Filled.Send, "", tint = Color.Gray)
                    },
                )
            }

            //counter message

        }

    }
}


@Preview
@Composable
private fun MenuScreenPreview() = PreviewContent {
    MenuContent()
}

