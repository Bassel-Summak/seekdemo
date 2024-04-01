package com.basapps.seekdemo.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.basapps.seekdemo.theme.AppTheme

@Composable
fun AppBar(
    title: String,
    navIcon: ImageVector? = null,
    isSearchEnabled :Boolean = false,
    isBackEnabled:Boolean = false,
    onSearch: () -> Unit = {},
    isProfileEnabled :Boolean = false,
    onProfile: () -> Unit = {},
    isLogOutEnabled :Boolean = false,
    onLogOut: () -> Unit = {},
    onNavBack: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
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
            // Add your icons here
            if (isSearchEnabled)
            IconButton(onClick = { onSearch() }) {
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
