package com.basapps.seekdemo.job.collection.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.basapps.seekdemo.components.AppBar
import com.basapps.seekdemo.components.AppUIPreview
import com.basapps.seekdemo.job.collection.presentation.components.ListDataItem
import com.basapps.seekdemo.job.collection.presentation.components.RectangularButton
import com.basapps.seekdemo.theme.AppTheme
import com.basapps.seekdemo.utils.toast

@Composable
fun  JobCollectionScreen(
    viewModel: JobCollectionViewModel,
    onJobClicked: (job:String) -> Unit,
    onNavigateToProfile: () -> Unit,
    navController: NavController,
    ) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current


    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver{_,event ->
            if (event == Lifecycle.Event.ON_RESUME){
                if (uiState.value.jobListStatus is JobListStatus.ShowData){
                    if (uiState.value.tabUI == Tab.ActiveJobs)
                        viewModel.onEvent(JobCollectionUiEvent.GetJobCollection)
                    else if (uiState.value.tabUI == Tab.Applied)
                        viewModel.onEvent(JobCollectionUiEvent.GetAppliedJobs)
                    else if (uiState.value.tabUI == Tab.Search){
                        if (uiState.value.jobListStatus is JobListStatus.ShowData){
                            viewModel.onEvent(JobCollectionUiEvent.GetQueryJobs((uiState.value.jobListStatus as JobListStatus.ShowData).jobsList.query))
                        }
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose{
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }


    JobCollection(
        uiState = uiState.value,
     { event ->
        viewModel.onEvent(event)
    },onNavigateToProfile= {
        onNavigateToProfile()
        }, onNavigateToJobDetails = {jobId->
            onJobClicked(jobId)
        },navController)

}

@Composable
fun JobCollection(
    uiState: JobCollectionUiState,
    onEvent: (event: JobCollectionUiEvent) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToJobDetails: (String) -> Unit,
    navController: NavController = NavHostController(LocalContext.current),

    ) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                title = "Home",
                navIcon = Icons.Filled.ArrowBack,
                isSearchEnabled = true,
                isSearchUIVisible = uiState.tabUI == Tab.Search,
                isProfileEnabled = true,
                onProfile = {onNavigateToProfile()},
                onNavBack = { navController.navigateUp() },
                onSearch = {
                    onEvent(JobCollectionUiEvent.GetQueryJobs(it))
                },
                onSearchUIVisible = {
                    onEvent(JobCollectionUiEvent.ShowSearchUI)

                },
                onCloseSearchPressed = {
                    onEvent(JobCollectionUiEvent.GetJobCollection)
                }
            )
        }

    ){ innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

            // Job List and its options view






            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (uiState.tabUI == Tab.Search){
                    var searchResults = ""
                    if (uiState.jobListStatus is JobListStatus.ShowData){
                        searchResults = "Search results for " + uiState.jobListStatus.jobsList.query
                    }
                    Box {
                        Text(text = searchResults)
                    }
                }
                else {
                    RectangularButton(
                        text = "Open Jobs",
                        onClick = { onEvent(JobCollectionUiEvent.GetJobCollection) },
                        selected = uiState.tabUI == Tab.ActiveJobs,
                        modifier = Modifier.weight(1f)
                    )

                    RectangularButton(
                        text = "Applied Jobs",
                        onClick = { onEvent(JobCollectionUiEvent.GetAppliedJobs) },
                        selected = uiState.tabUI == Tab.Applied,
                        modifier = Modifier.weight(1f)
                    )
                }

            }




            Column (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                ListDataItem(
                    jobListStatus = uiState.jobListStatus,
                    modifier = Modifier,
                    onNextItemRequest = {
                        if (!uiState.isListRequested)
                        onEvent(JobCollectionUiEvent.GetNextJobCollectionPage(it))
                    },
                    onClickRetry = {
                        onEvent(JobCollectionUiEvent.RetryLoadingList)
                    },
                    onClickJobListItem = { itemId ->
                        onNavigateToJobDetails(itemId.id)
                    },
                )
            }
        }
    }
    }
}

@AppUIPreview
@Composable
fun JobCollectionPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            JobCollection(JobCollectionUiState() ,{},{},{})
        }
    }
}
