package com.basapps.seekdemo.common.utils.validator

interface InputValidator {
    fun validate(input: String): ValidationResult
}
