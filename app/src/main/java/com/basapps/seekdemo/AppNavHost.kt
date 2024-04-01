package com.basapps.seekdemo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.basapps.seekdemo.job.presentation.authNavGraph
import com.basapps.seekdemo.job.presentation.authRoute
import com.basapps.seekdemo.job.collection.presentation.JobCollectionScreen
import com.basapps.seekdemo.job.detail.presentation.JobDetailsScreen
import com.basapps.seekdemo.profile.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    val listRoute = "listRoute"
    val detailsRoute = "detailsRoute"
    val profileRoute = "profileRoute"

    NavHost(
        navController = navController,
        startDestination = authRoute,
    ) {
        authNavGraph(
            navController = navController,
            onAuthSuccess = {
                navController.navigate(
                    dest = listRoute,
                    popUpTo = authRoute,
                )
            },
        )


        setComposable(listRoute) {
            JobCollectionScreen(viewModel = hiltViewModel(),
                navController = navController, onJobClicked ={jobId ->
                    navController.navigate("$detailsRoute/${jobId}")

                }, onNavigateToProfile = {
                    navController.navigate(profileRoute)
                }
              )
        }

        setComposable("$detailsRoute/{jobId}") {backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")

            jobId?.let {
                JobDetailsScreen(viewModel = hiltViewModel(), onDone ={
                    navController.navigateUp()
                }, navController = navController, jobId = jobId)
            }
        }

        setComposable(profileRoute) {
            ProfileScreen(viewModel = hiltViewModel(), navController,onLogout = {
                navController.navigate(authRoute) {
                    popUpTo(0)
                }
            })

        }




    }
}

fun NavHostController.navigate(dest: String, popUpTo: String, inclusive: Boolean = true) {
    navigate(dest) {
        popUpTo(popUpTo) {
            this.inclusive = inclusive
        }
    }
}
