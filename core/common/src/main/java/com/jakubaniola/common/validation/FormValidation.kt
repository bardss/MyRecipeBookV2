package com.jakubaniola.common.validation

import com.jakubaniola.common.validation.ValidationType.EMPTY
import com.jakubaniola.common.validation.ValidationType.NUMBER

fun validate(
    value: String,
    vararg type: ValidationType
): ValidationResult {
    var valid = ValidationResult.VALID
    type.forEach {
        valid = when (it) {
            NUMBER -> if (value.toIntOrNull() == null) {
                ValidationResult.MUST_BE_NUMBER
            } else ValidationResult.VALID
            EMPTY -> if (value.isNotEmpty()) {
                ValidationResult.VALID
            } else ValidationResult.EMPTY
        }
    }
    return valid
}
