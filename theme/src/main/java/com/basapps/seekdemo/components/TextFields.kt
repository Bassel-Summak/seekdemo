package com.basapps.seekdemo.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basapps.seekdemo.theme.AppTheme

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    @StringRes label: Int,
    hint: String = "",
    maxChar : Int =-1,
    backgroundColor: Color? = null,
    onValueChanged: (value: String) -> Unit,
    isPasswordField: Boolean = false,
    isClickOnly: Boolean = false,
    onClick: () -> Unit = {},
    leadingIcon: ImageVector? = null,
    @StringRes error: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current


        Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp),
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    if (isClickOnly) {
                        onClick()
                    }
                },
            value = value,
            onValueChange = {
                if (it.length != maxChar)
                onValueChanged(it)
                            },
            singleLine = true,
            isError = error != null,
            readOnly = isClickOnly,
            enabled = !isClickOnly,
            supportingText = {
                if (error != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(error),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
                    visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
            label = { Text(text = stringResource(label)) },
            placeholder = { Text(text = hint) },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(it, hint, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            },

            colors = if (backgroundColor!=null) { // Replace 'condition' with your actual condition
                TextFieldDefaults.colors(
                    focusedContainerColor = backgroundColor,
                    unfocusedContainerColor = backgroundColor,
                    disabledTextColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent

                )
            } else {
                TextFieldDefaults.colors() // Default colors
            },

            trailingIcon = {
                if (error != null) {
                    Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                }
            },
                    keyboardActions = KeyboardActions(
                onDone = {
                        focusManager.clearFocus()
                        onDone()

                },
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
                        onSearch = {
                            focusManager.clearFocus()
                            onDone()
                        }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
        )
    }
}

@AppUIPreview
@Composable
fun AppTextFieldPreview() {
    AppTheme {
        Surface {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {},
                value = "",
                onValueChange = { },
                singleLine = true,
                isError = true, // @Update Error State
                readOnly = false,
                enabled = true,
                supportingText = {
                    Text(text = "Error Message or Supporting Message")
                },
                placeholder = { Text(text = "Hint") },
            )
        }
    }
}
