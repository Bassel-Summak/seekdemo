package com.basapps.seekdemo.profile

import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.components.UnidirectionalViewModel
import com.basapps.seekdemo.storage.CurrentUser

interface ProfileContract :
    UnidirectionalViewModel<ProfileContract.State, ProfileContract.Event,
            ProfileContract.Effect> {

    data class State(
        val user :  CurrentUser = CurrentUser("","","","","") ,
        val isLoadingDisplayName :  Boolean = false,
        val dialogState: DialogsState = DialogsState ()
    )



    data class DialogsState(
        val isDialogEditUserVisible: Boolean = false,
        val isDialogLogOutVisible: Boolean = false,
    )


    sealed class Event {
        data object LogOut : Event()
        data class UpdateUserName(val name: String) : Event()
        data class UpdateDialogsState(val dialogsState: DialogsState) : Event()
    }

    sealed class Effect {
        data class ShowToastMessageString (val message: String) : Effect()
        data object LogOut  : Effect()

    }


    sealed class JobDetailsStatus {
        data object Loading : JobDetailsStatus()
        data object Retrying : JobDetailsStatus()
        data class ShowData(val job: Job) : JobDetailsStatus()
    }



}
