package com.basapps.seekdemo.job.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.job.domain.UserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCase: UserDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Splash())
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        isLoggedIn()
    }

    private fun isLoggedIn() = viewModelScope.launch {
        _uiState.value = SplashUiState.Splash(isLoading = true)
        when (splashUseCase.invoke()) {
            is Resource.Error -> _uiState.value = SplashUiState.Splash(moveToLogin = true)
            is Resource.Success -> _uiState.value = SplashUiState.Authenticated
        }
    }
}
