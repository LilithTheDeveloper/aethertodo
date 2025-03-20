package de.vakyria.aethertodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Pill(
    modifier: Modifier = Modifier,
    text: String = "Pill"
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(100.dp)
            .height(30.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.shapes.medium
            )
    ){
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}


@Preview
@Composable
private fun PillPreview() {
    MaterialTheme{
        Pill(

        )
    }
}