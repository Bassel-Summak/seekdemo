package com.basapps.seekdemo.job.detail.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.components.AppBar
import com.basapps.seekdemo.components.AppUIPreview
import com.basapps.seekdemo.components.UseFromViewModel
import com.basapps.seekdemo.components.collectInLaunchedEffect
import com.basapps.seekdemo.theme.AppTheme
import com.basapps.seekdemo.utils.toast

@Composable
fun  JobDetailsScreen(
    viewModel: JobDetailsViewModel,
    navController: NavController,
    jobId:String,
    onDone: () -> Unit,
) {
    // val uiState = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val (uiState,onEvent,effect) = UseFromViewModel(viewModel = viewModel)

    effect.collectInLaunchedEffect{
        when(it){
            JobDetailsContract.Effect.JobIsApplied -> {
                onDone()
            }
            is JobDetailsContract.Effect.ShowToastMessageString -> {
                context.toast(it.message){}
            }

            JobDetailsContract.Effect.GetJobDetails -> {
                onEvent(JobDetailsContract.Event.getJobDetails(jobId))
            }
        }
    }
    AnimatedContent(targetState = uiState.jobDetailsStatus,
        transitionSpec = {
            (fadeIn() + slideInVertically(animationSpec = tween(300),
                initialOffsetY = { fullHeight -> fullHeight })).togetherWith(
                fadeOut(
                    animationSpec = tween(200)
                )
            )
        }){stateJobDetails ->

        when (stateJobDetails){
            JobDetailsContract.JobDetailsStatus.Loading -> {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                }

            }
            JobDetailsContract.JobDetailsStatus.Retrying -> {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Make sure you have internet connection then try again",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Button(
                        modifier = Modifier
                            .width(width = 120.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .height(height = 64.dp)
                            .padding(top = 8.dp),
                        onClick = {
                            viewModel.onEvent(JobDetailsContract.Event.getJobDetails(jobId))
                        },

                        ) {
                        Text(
                            text = "Retry",
                        )
                    }
                }
            }
            is JobDetailsContract.JobDetailsStatus.ShowData -> {

                JobDetails(
                    uiState = uiState,
                    { event ->
                        viewModel.onEvent(event)
                    },
                    navController,
                    stateJobDetails.job)

            }

        }
    }




}

@Composable
fun JobDetails(
    uiState: JobDetailsContract.State,
    onEvent: (event: JobDetailsContract.Event) -> Unit,
    navController: NavController = NavHostController(LocalContext.current),
    item:Job,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                title = "Details",
                navIcon = Icons.Filled.ArrowBack,
                isBackEnabled = true,
                onNavBack = { navController.navigateUp() },
            )
        }

    ){ innerPadding ->

        Column (  modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {

            Row(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 25.dp))
                {

                        Text(
                            text = item.positionTitle,
                            style = MaterialTheme.typography.titleLarge,
                        )

                        Text(
                            text = "Company Name",
                            style = MaterialTheme.typography.bodySmall,
                        )

                    Image(
                        painter = painterResource(id = R.drawable.company_img),
                        contentDescription = "image of the company",
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .fillMaxWidth()
                            .size(width = 10.dp, height = 200.dp),


                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )

                    Text(
                        text = "Starting from "+ item.minSalary + "$ until " +
                        item.maxSalary + "$",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 75.dp),
                         // Adjust the value as needed
                    )


                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 25.dp),

                    )

                }
            }


            if (item.haveIApplied) {
                Text(
                    text = "Applied ✔️\nWe will email you soon!",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center ,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .wrapContentSize(Alignment.Center)
                )

            }
            else {
                CustomButtonWithLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "Apply Now!",
                    isLoading = uiState.isAddJobButtonLoading,
                    onClick = {
                        onEvent.invoke(JobDetailsContract.Event.applyForJob(item.id))
                    },
                )
            }

        }

    }



}

@AppUIPreview
@Composable
fun JobCollectionPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            JobDetails(JobDetailsContract.State(),{}, item = Job(
                id = "",
                description = "Text Job junrvunvflkj fkdjv rjvn rkijdnikjudn juikdbgndui bnuibndkjg dn fdfdknfdjlkgfd",
                haveIApplied = false,
                industry = 3,
                location = 1,
                positionTitle = "Best Man",
                maxSalary = "23232",
                minSalary = "32323232"
            )
            )
        }
    }
}
