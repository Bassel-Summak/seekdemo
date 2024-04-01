package com.basapps.seekdemo.job.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.basapps.seekdemo.auth.presentation.R
import com.basapps.seekdemo.job.presentation.AuthScreen
import com.basapps.seekdemo.components.AppUIPreview
import com.basapps.seekdemo.theme.AppTheme

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navController: NavController,
    onAuthSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState.value) {
        SplashUiState.Authenticated -> {
            LaunchedEffect(Unit) { onAuthSuccess() }
        }

        is SplashUiState.Splash -> {
            if (state.moveToLogin) {
                LaunchedEffect(Unit) {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                Splash(state)
            }
        }
    }
}

@Composable
fun Splash(state: SplashUiState.Splash) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.seek_logo),
            contentDescription = "mini tales",
        )
        Spacer(modifier = Modifier.size(12.dp))
        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.size(20.dp),
            )
         //   CircularProgressIndicator()
        }
    }
}

@AppUIPreview
@Composable
fun SplashPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Splash(SplashUiState.Splash())
        }
    }
}
