package com.basapps.seekdemo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basapps.seekdemo.theme.AppTheme
import com.basapps.seekdemo.theme.R

@Composable
fun AppBar(
    title: String,
    navIcon: ImageVector? = null,
    isSearchEnabled :Boolean = false,
    onSearchUIVisible : () -> Unit = {},
    isSearchUIVisible:Boolean =false,
    isBackEnabled:Boolean = false,
    onSearch: (String) -> Unit = {},
    onCloseSearchPressed: () -> Unit = {},
    isProfileEnabled :Boolean = false,
    onProfile: () -> Unit = {},
    isLogOutEnabled :Boolean = false,
    onLogOut: () -> Unit = {},
    onNavBack: () -> Unit = {},
) {

    val textState = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var textFieldLoaded by remember { mutableStateOf(false) }


    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            if (!isSearchUIVisible)
            Text(text = title, fontSize = 16.sp,)
        },
        navigationIcon = {
            if (isBackEnabled)
            navIcon?.let {
                IconButton(onClick = { onNavBack() }) {
                    Icon(navIcon, contentDescription = "Nav Icon")
                }
            }
        },
        actions = {

            if (!isSearchUIVisible){
                textState.value = ""
                // Add your icons here
                if (isSearchEnabled)
                    IconButton(onClick = { onSearchUIVisible()
                        if (textFieldLoaded)  focusRequester.requestFocus()}) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                if (isProfileEnabled)
                    IconButton(onClick = { onProfile() }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                if (isLogOutEnabled)
                    IconButton(onClick = { onLogOut() }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "LogOut")
                    }
            }

            AnimatedVisibility(visible = isSearchUIVisible) {

                if (isSearchUIVisible){
                    Row(
                        modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.primaryContainer),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTextField(
                            modifier = Modifier.weight(1f).padding(top = 10.dp)
                                .focusRequester(focusRequester).onGloballyPositioned {
                                    if (!textFieldLoaded) {
                                        focusRequester.requestFocus()
                                        textFieldLoaded = true
                                    }
                                },
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            value = textState.value,
                            label = R.string.search_label,
                            hint = "Search for job",
                            leadingIcon = Icons.Filled.Search,
                            onValueChanged = {
                                textState.value = it },
                            onDone = {
                                if (textState.value.isNotEmpty())
                                onSearch(textState.value)
                            },
                            imeAction = ImeAction.Search,
                            maxChar = 20)

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                textFieldLoaded = false
                                onCloseSearchPressed()
                            },
                        ) {
                            Icon(imageVector = Icons.Default.Close,contentDescription = "LogOut")
                        }

                    }

                }
            }

        }
    )
}

@Composable
@AppUIPreview
private fun AppBarPreview() {
    AppTheme {
        Surface {
            AppBar(
                title = "Seek Demo",
                navIcon = Icons.Filled.ArrowBack,
            )
        }
    }
}
