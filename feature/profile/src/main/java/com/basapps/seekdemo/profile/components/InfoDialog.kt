package com.basapps.seekdemo.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.basapps.seekdemo.components.AppUIPreview
import com.basapps.seekdemo.theme.AppTheme
import dev.belalkhan.minitales.profile.R

@AppUIPreview
@Composable
fun InfoDialog(
    title: String?="Message",
    desc: String?="Body",
    showHeaderIcon :Boolean = true,
    showPositiveButton:Boolean = true,
    showNegativeButton:Boolean = true,
    textPositiveRes:Int= R.string.yes,
    textNegativeRes:Int= R.string.no,
    onPositive: () -> Unit = {},
    onNegative: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AppTheme {
        Dialog(
            onDismissRequest = onDismiss
        ) {

            Box(
                modifier = Modifier
                    .height(460.dp)
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Spacer(modifier = Modifier.height(130.dp))
                    Box(
                        modifier = Modifier
                            .height(490.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                color = MaterialTheme.colorScheme.onSurface,
                                text = title!!,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            Spacer(modifier = Modifier.height(8.dp))


                            Text(
                                color = MaterialTheme.colorScheme.onSurface,
                                text = desc!!,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Spacer(modifier = Modifier.height(24.dp))


                            if (showPositiveButton)
                                Button(
                                    onClick = {
                                        onPositive()
                                        onDismiss()
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(5.dp)),
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = stringResource(id = textPositiveRes),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                    )
                                }

                            if (showNegativeButton)
                                ElevatedButton(
                                    onClick = {
                                        onNegative()
                                        onDismiss()
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(5.dp))
                                ) {
                                    Text(
                                        text = stringResource(id = textNegativeRes),
                                        color = MaterialTheme.colorScheme.secondary,
                                    )
                                }


                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                if (showHeaderIcon)
                    HeaderImage(
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.TopCenter)
                    )
            }
        }
    }

}
