package com.basapps.seekdemo.job.presentation.validators

import com.basapps.seekdemo.auth.presentation.R
import com.basapps.seekdemo.common.utils.validator.InputValidator
import com.basapps.seekdemo.common.utils.validator.ValidationResult

class EmailValidator : InputValidator {

    private val emailPattern = Regex(
        "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,}$",
        RegexOption.IGNORE_CASE,
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isEmpty()) {
            ValidationResult(R.string.error_email_empty)
        } else if (!emailPattern.matches(input)) {
            return ValidationResult(R.string.error_email_invalid)
        } else {
            ValidationResult()
        }
    }
}
