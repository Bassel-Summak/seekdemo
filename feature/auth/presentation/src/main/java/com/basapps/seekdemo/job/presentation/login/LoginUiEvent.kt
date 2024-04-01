package com.basapps.seekdemo.job.presentation.login

sealed class LoginUiEvent {
    data class UserNameChanged(val email: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    data object Login : LoginUiEvent()
    data object ForgetPassword : LoginUiEvent()
    data object Signup : LoginUiEvent()
}
