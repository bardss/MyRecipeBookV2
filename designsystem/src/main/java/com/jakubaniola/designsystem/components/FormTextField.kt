package com.jakubaniola.designsystem.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.jakubaniola.common.FieldValue

@ExperimentalMaterial3Api
@Composable
fun FormField(
    modifier: Modifier,
    fieldValue: FieldValue,
    labelStringId: Int,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    isThereNextField: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = fieldValue.value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = labelStringId)) },
        maxLines = maxLines,
        isError = fieldValue.isError(),
        supportingText = {
            if (fieldValue.isError()) {
                Text(text = stringResource(id = fieldValue.validationResult.errorTextId))
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = if (isThereNextField) ImeAction.Next else ImeAction.Default,
            keyboardType = keyboardType
        ),
        singleLine = maxLines == 1,
    )
}
