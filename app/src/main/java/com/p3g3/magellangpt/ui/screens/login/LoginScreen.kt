package com.p3g3.magellangpt.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.p3g3.magellangpt.R
import com.p3g3.magellangpt.ui.core.Destination
import com.p3g3.magellangpt.ui.core.composables.PreviewContent
import com.p3g3.magellangpt.ui.core.navigate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private typealias UIState = LoginState
private typealias UIAction = LoginAction

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = viewModel()
    val state by viewModel.state.collectAsState()


    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is Destination.Menu)
                    navController.navigate(destination = event)
            }.collect()
    }
    LoginContent(
        state = state,
        onAction = viewModel::handleAction
    )

}


@Composable
private fun LoginContent(
    state: UIState = UIState(),
    onAction: (UIAction) -> Unit = { }
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xff0d506c), Color(0xff00385d))))
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "MagellanGPT",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize(0.5f)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "BottomImage",
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.BottomEnd,
            )
            ButtonMicrosoft()
        }


    }
}

@Composable
private fun ButtonMicrosoft() {
    Button(
        onClick = { /*TODO*/ }, shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.img_2), contentDescription = "")
        }

    }
}

@Preview
@Composable
private fun ButtonMicrosoftPreview() = PreviewContent {
    ButtonMicrosoft()
}


@Preview
@Composable
private fun LoginPreview() = PreviewContent {
    LoginContent()
}