package com.jakubaniola.common

import com.jakubaniola.common.validation.ValidationResult
import com.jakubaniola.common.validation.ValidationType
import com.jakubaniola.common.validation.validate

data class FieldValue(
    val value: String,
    val validationResult: ValidationResult = ValidationResult.VALID,
) {
    fun isError() = validationResult != ValidationResult.VALID
}

fun validateAndCopy(
    newValue: String,
    vararg validationType: ValidationType
) = FieldValue(
    value = newValue,
    validationResult = validate(newValue, *validationType),
)
