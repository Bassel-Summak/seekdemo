package com.basapps.seekdemo.job.presentation.validators

import com.basapps.seekdemo.common.utils.validator.InputValidator
import javax.inject.Inject

class ValidatorFactory @Inject constructor() {

    private val validators: Map<AuthParams, InputValidator> = mapOf(
        AuthParams.FULL_NAME to FullNameValidator(),
        AuthParams.USER_NAME to FullNameValidator(),
        AuthParams.PASSWORD to PasswordValidator(),
    )

    fun get(param: AuthParams): InputValidator {
        return validators.getOrElse(param) {
            throw IllegalArgumentException("Validator not found; make sure you have provided correct param")
        }
    }
}
