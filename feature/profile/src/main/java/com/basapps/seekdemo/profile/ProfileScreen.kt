package com.basapps.seekdemo.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.basapps.seekdemo.common.domain.models.User
import com.basapps.seekdemo.components.AppBar
import com.basapps.seekdemo.components.AppUIPreview
import com.basapps.seekdemo.components.UseFromViewModel
import com.basapps.seekdemo.components.collectInLaunchedEffect
import com.basapps.seekdemo.profile.components.EditUserNameDialog
import com.basapps.seekdemo.profile.components.InfoDialog
import com.basapps.seekdemo.storage.CurrentUser
import com.basapps.seekdemo.theme.AppTheme
import com.basapps.seekdemo.utils.toast
import dev.belalkhan.minitales.profile.R

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController,
                  onLogout: () -> Unit) {

    val (uiState,onEvent,effect) = UseFromViewModel(viewModel = viewModel)
    val context = LocalContext.current
    effect.collectInLaunchedEffect{
        when(it){

            ProfileContract.Effect.LogOut -> {
                onLogout()
            }
            is ProfileContract.Effect.ShowToastMessageString -> {
                context.toast(it.message){}
            }
        }
    }

    Profile(uiState,
        onEvent = { event ->
            viewModel.onEvent(event)
        },navController)
}

@Composable
private fun Profile(
    uiState: ProfileContract.State,
    onEvent: (event: ProfileContract.Event) -> Unit,
    navController: NavController = NavHostController(LocalContext.current),
    ) {
    Box {
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

        ){padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
            ) {
                Divider()
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (box, image) = createRefs()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(12.dp)
                            .constrainAs(box) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                    )

                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .constrainAs(image) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(box.bottom, (-50).dp)
                            },
                        painter = painterResource(id = R.drawable.boy),
                        contentDescription = "profile image",
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    if (uiState.isLoadingDisplayName){
                        LinearProgressIndicator(
                            modifier = Modifier.size(20.dp).align(CenterHorizontally)
                        )
                    }
                    else
                        Row(modifier = Modifier
                            .padding(top = 5.dp)
                            .align(CenterHorizontally)
                            .clickable(onClick = {
                                onEvent(
                                    ProfileContract.Event.UpdateDialogsState(
                                        uiState.dialogState.copy(
                                            isDialogEditUserVisible = true
                                        )
                                    )
                                )
                            }),     verticalAlignment = Alignment.CenterVertically
                        ){

                                Text(modifier = Modifier.padding(end = 5.dp),
                                    text = uiState.user.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Center,)

                                    Icon(imageVector = Icons.Filled.Edit, "edit")

                            }




                    Text(
                        modifier = Modifier.align(CenterHorizontally),
                        text = "This earth is round therefore I believe nothing will be down",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.size(24.dp))



                    Row(modifier = Modifier
                        .padding(top = 14.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Person, "email")


                        Text(
                            modifier = Modifier.padding(start = 24.dp, end = 12.dp),
                            text = uiState.user.username,
                            style = MaterialTheme.typography.titleMedium,
                        )



                    }

                    Row(modifier = Modifier.padding(top = 14.dp)) {
                        Icon(imageVector = Icons.Filled.Phone, "phone")

                        Text(
                            modifier = Modifier.padding(start = 24.dp),
                            text = "+601124020818",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }

                    Row(modifier = Modifier.padding(top = 14.dp)) {
                        Icon(imageVector = Icons.Filled.LocationOn, "location")

                        Text(
                            modifier = Modifier.padding(start = 24.dp),
                            text = "Malaysia, KL",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }

                    OutlinedButton(
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .align(CenterHorizontally),
                        onClick = { onEvent(ProfileContract.Event.UpdateDialogsState(uiState.dialogState.copy(isDialogLogOutVisible  = true))) },
                    ) {
                        Text(text = stringResource(R.string.logout))
                    }
                }
            }
        }

        if (uiState.dialogState.isDialogLogOutVisible){
            InfoDialog(
                title = "Are you sure you want to sign out?",
                desc = "",
                onDismiss = {
                    onEvent(ProfileContract.Event.UpdateDialogsState(uiState.dialogState.copy(isDialogLogOutVisible  = false)))
                },
                onPositive = {
                    onEvent(ProfileContract.Event.LogOut)
                },

            )
        }

        if (uiState.dialogState.isDialogEditUserVisible){
            EditUserNameDialog(
                onDismiss = {
                    onEvent(ProfileContract.Event.UpdateDialogsState(uiState.dialogState.copy(isDialogEditUserVisible = false)))
                },
                onPositive = {
                    onEvent(ProfileContract.Event.UpdateUserName(it))
                },
                currentValue = uiState.user.name

            )
        }

    }


}

@AppUIPreview
@Composable
private fun ProfilePreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Profile(ProfileContract.State(CurrentUser("","","dsdsd","","")),{})
        }
    }
}
