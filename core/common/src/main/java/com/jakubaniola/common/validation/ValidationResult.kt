package com.jakubaniola.common.validation

import com.jakubaniola.common.R

enum class ValidationResult(
    val errorTextId: Int
) {
    VALID(R.string.empty),
    EMPTY(R.string.invalid_empty),
    MUST_BE_NUMBER(R.string.invalid_must_be_number),
    INVALID(R.string.invalid_value)
}
