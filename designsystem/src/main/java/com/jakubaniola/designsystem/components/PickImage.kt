package com.jakubaniola.designsystem.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@Composable
fun PickImage(
    modifier: Modifier = Modifier,
    imageUri: String,
    onClick: () -> Unit = { }
) {
    val doesImageExist = imageUri.isNotEmpty()
    val alpha = if (doesImageExist) 1f else 0.5f
    Box(
        modifier = modifier
            .padding(40.dp, 30.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.extraSmall,
            )
            .alpha(alpha)
            .fillMaxWidth()
            .padding(0.dp, 30.dp)
            .heightIn(0.dp, 330.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        if (doesImageExist) {
            AsyncImage(
                model = Uri.parse(imageUri),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )
        } else {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Pick image button",
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PickImagePreview() {
    MyRecipeBookTheme {
        PickImage(
            imageUri = ""
        )
    }
}
