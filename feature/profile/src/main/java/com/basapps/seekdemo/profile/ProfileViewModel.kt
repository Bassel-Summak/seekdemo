package com.basapps.seekdemo.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.ResourceError
import com.basapps.seekdemo.common.domain.usecases.UpdateUserUseCase
import com.basapps.seekdemo.common.domain.usecases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.basapps.seekdemo.storage.SessionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionHandler: SessionHandler,
    private val logoutUseCase: LogoutUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel(),ProfileContract {

    private val _state = MutableStateFlow(ProfileContract.State())
    override val state: StateFlow<ProfileContract.State> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<ProfileContract.Effect>()
    override val effect: SharedFlow<ProfileContract.Effect> = _effectFlow.asSharedFlow()


    init {
        getUserDetail()
    }

    override fun onEvent(uiEvent: ProfileContract.Event) {
        when (uiEvent) {
            is ProfileContract.Event.LogOut -> {
                logout()
            }
            is ProfileContract.Event.UpdateUserName -> {
                updateUserName(uiEvent.name)
            }
            is ProfileContract.Event.UpdateDialogsState -> {
                _state.update { it.copy(dialogState = uiEvent.dialogsState) }
            }
        }
    }

    private fun updateUserName(name:String) = viewModelScope.launch {
        _state.update{it.copy(isLoadingDisplayName = true)}
        when (val useCase = updateUserUseCase.invoke(name)) {
            is Resource.Success -> {
                val user = _state.value.user
                user.name = name
                _state.update { it.copy(isLoadingDisplayName = false, user =user ) }
                sessionHandler.setCurrentUser(id = user.id, img = user.img, name = user.name, username = user.username, authKey = null)
                showMessage("User name has been updated!")
            }
            is Resource.Error -> {
                showMessage(getError(useCase))
                _state.update { it.copy(isLoadingDisplayName = false) }
            }
        }
    }

    private fun showMessage(string: String){
        viewModelScope.launch {
            _effectFlow.emit(
                ProfileContract.Effect.ShowToastMessageString(string)
            )
        }
    }

    private fun getUserDetail() = viewModelScope.launch {
        val user = sessionHandler.getCurrentUser().first()
        println("username " + user.username)
        _state.update { it.copy(user = user) }
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase.invoke()
        viewModelScope.launch {
            _effectFlow.emit(
                ProfileContract.Effect.LogOut
            )
        }
    }


    private fun getError(loginError: Resource.Error): String {
        return when (loginError.e) {
            ResourceError.UNAUTHORIZED -> "Session is expired"
            ResourceError.SERVICE_UNAVAILABLE -> "Service not available"
            ResourceError.UNKNOWN ->  "Unable to apply for the job"
        }
    }
}
