package com.basapps.seekdemo.job.presentation.validators

import com.basapps.seekdemo.auth.presentation.R
import com.basapps.seekdemo.common.utils.validator.InputValidator
import com.basapps.seekdemo.common.utils.validator.ValidationResult

class FullNameValidator : InputValidator {

    private val namePattern = Regex(
        "/^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*\$/g",
        RegexOption.IGNORE_CASE,
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isEmpty()) {
            ValidationResult(R.string.error_name_empty)
        } else if (namePattern.matches(input)) {
            return ValidationResult(R.string.error_name_invalid)
        } else {
            ValidationResult()
        }
    }
}
