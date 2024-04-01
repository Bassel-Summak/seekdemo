package com.basapps.seekdemo.job.collection.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.components.AppUIPreview
import com.basapps.seekdemo.job.collection.presentation.R
import com.basapps.seekdemo.theme.AppTheme

@AppUIPreview
@Composable
fun JobsItem(
    item: Job = Job(
        id = "",
        description = "Text Job junrvunvflkj fkdjv rjvn rkijdnikjudn juikdbgndui bnuibndkjg dn fdfdknfdjlkgfd",
        haveIApplied = false,
        industry = 3,
        location = 1,
        positionTitle = "Best Man",
        maxSalary = "23232",
        minSalary = "32323232"

    ),
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    AppTheme {
        Row(
            modifier = Modifier
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.onTertiary)
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column (Modifier.weight(1f)) {

                Row{
                    Text(
                        text = item.positionTitle,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Text(
                    text = "Starting from "+ item.minSalary + "$",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )


                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), // Adjust the value as needed
                    maxLines = 3
                )

            }


            Column (Modifier.weight(0.2f)) {

                if (!item.haveIApplied) {
                    Text(
                        text = "Apply\nNow!",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ) ,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF02A002),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .wrapContentSize(Alignment.Center)
                    )

                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                            .size(15.dp)
                            .drawBehind {
                                drawCircle(
                                    color = Color.DarkGray,
                                    radius = this.size.maxDimension
                                )
                            },
                        painter = painterResource(R.drawable.ic_arrow_right_black),
                        contentDescription = "Icon to switch back to Partner",
                        tint = White,
                    )
                } else {
                    Text(
                        text = "Applied\n    ✔️",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .wrapContentSize(Alignment.Center)
                    )

                }


            } }
    }


    }
