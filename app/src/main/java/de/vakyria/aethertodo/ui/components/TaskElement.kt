package de.vakyria.aethertodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.vakyria.aethertodo.database.defaults.DefaultTasks
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
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column{
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = task?.description ?: "",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Pill(
                    text = task.status,
                )
                Pill(
                    text = "Priorität: ${task.priority}",
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaskElementPreview() {
    MaterialTheme() {
        TaskElement(
            task = DefaultTasks.exampleTask
        )
    }
}