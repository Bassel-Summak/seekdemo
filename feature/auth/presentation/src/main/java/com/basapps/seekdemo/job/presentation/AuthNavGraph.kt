package com.basapps.seekdemo.job.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.basapps.seekdemo.job.presentation.login.LoginScreen
import com.basapps.seekdemo.job.presentation.splash.SplashScreen

const val authRoute = "auth"

sealed class AuthScreen(val route: String) {
    data object Splash : AuthScreen("$authRoute/splash")
    data object Login : AuthScreen("$authRoute/login")
}

fun NavGraphBuilder.authNavGraph(
    onAuthSuccess: () -> Unit,
    navController: NavController,
) {
    navigation(startDestination = AuthScreen.Splash.route, route = authRoute) {
        composable(AuthScreen.Splash.route) {
            SplashScreen(
                viewModel = hiltViewModel(),
                navController = navController,
                onAuthSuccess = onAuthSuccess,
            )
        }

        composable(AuthScreen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onAuthSuccess = onAuthSuccess,
                onNavigateToSignup = {
                    // navController.navigate(AuthScreen.Signup.route)
                },
            )
        }
    }
}
