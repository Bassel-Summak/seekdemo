package com.basapps.seekdemo.job.collection.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.components.AppTextField
import com.basapps.seekdemo.job.collection.presentation.JobListStatus
import com.basapps.seekdemo.job.collection.presentation.R


@Composable
fun ListDataItem(
    onClickRetry: () -> Unit = {},
    onNextItemRequest: (Int) -> Unit = {},
    onClickJobListItem: (Job) -> Unit = {},
    modifier:Modifier = Modifier,
    jobListStatus: JobListStatus =JobListStatus.Loading
){
    val lazyListState = rememberLazyListState()

    val isScrollToEnd by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(targetState = jobListStatus,
            transitionSpec = {
                (fadeIn() + slideInVertically(animationSpec = tween(300),
                    initialOffsetY = { fullHeight -> fullHeight })).togetherWith(
                    fadeOut(
                        animationSpec = tween(200)
                    )
                )
        }) {jobListStatus->

            when (jobListStatus){
                JobListStatus.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                }
                JobListStatus.Empty -> {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        Icon(
                            modifier = Modifier.padding(bottom = 8.dp).size(80.dp),
                            painter = painterResource(R.drawable.empty_nodataicon),
                            contentDescription = "Icon to switch back to Partner",
                            tint = Color.Unspecified,
                        )

                        Text(
                            text = "No Job to show :(",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }


                }
                JobListStatus.Retrying -> {
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
                                onClickRetry()
                            },

                        ) {
                            Text(
                                text = "Retry",
                            )
                        }
                    }
                }
                is JobListStatus.ShowData -> {

                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){

                        items(jobListStatus.jobsList.jobs){ item ->
                            JobsItem(
                                item = item,
                                onClick = { onClickJobListItem(item) },
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }



                        if (jobListStatus.jobsList.isThereMorePages) {
                            val visibleItemCount = lazyListState.layoutInfo.visibleItemsInfo.size
                            val totalItemCount = jobListStatus.jobsList.jobs.size
                            val lastVisibleItemIndex = lazyListState.layoutInfo.totalItemsCount - 1

                            if (isScrollToEnd){
                                onNextItemRequest(jobListStatus.jobsList.page+1)

                            }
                        }
                    }




                }

                is JobListStatus.None -> {}
            }
    }
    }
}