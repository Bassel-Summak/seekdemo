package com.basapps.seekdemo.job.presentation.validators

import com.basapps.seekdemo.auth.presentation.R
import com.basapps.seekdemo.common.utils.validator.InputValidator
import com.basapps.seekdemo.common.utils.validator.ValidationResult

class PasswordValidator : InputValidator {

    override fun validate(input: String): ValidationResult {
        return if (input.length < 6) {
            ValidationResult(R.string.error_password)
        } else {
            ValidationResult()
        }
    }
}
