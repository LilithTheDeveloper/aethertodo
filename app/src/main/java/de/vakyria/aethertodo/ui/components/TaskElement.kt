package de.vakyria.aethertodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sqldelight.de.vakyria.aethertodo.database.models.Task

@Composable
fun TaskElement(
    modifier: Modifier = Modifier,
    task: Task
) {

    Card(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background,
                MaterialTheme.shapes.medium
            )
            .fillMaxWidth()
            .height(80.dp)
    ){
        Row{
            Column{
            }

        }
    }
}

@Preview
@Composable
private fun TaskElementPreview() {
    MaterialTheme() {
        TaskElement()
    }
}