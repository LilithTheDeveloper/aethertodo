package de.vakyria.aethertodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import de.vakyria.aethertodo.database.defaults.DefaultTasks
import kotlinx.serialization.json.JsonNull.content
import sqldelight.de.vakyria.aethertodo.database.models.Task
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalCreateTask(
    modifier: Modifier = Modifier,
    onConfirmSelection: (Task) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val datePickerState = remember { DatePickerState(
        locale = CalendarLocale.GERMAN
    ) }

    val task = remember { mutableStateOf(DefaultTasks.emptyTask) }

    val openDatePicker = remember { mutableStateOf(false) }

    LaunchedEffect(datePickerState.selectedDateMillis) {
        val formattedDate = Calendar.getInstance().apply {
            timeInMillis = datePickerState.selectedDateMillis ?: 0
        }
        task.value = task.value.copy(
            dueDate = "${formattedDate.get(Calendar.DAY_OF_MONTH)}.${formattedDate.get(Calendar.MONTH)}.${formattedDate.get(Calendar.YEAR)}"
        )
    }


    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            modifier = modifier
                .fillMaxHeight()
                .background(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Task erstellen",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = task.value.title,
                        onValueChange = {
                            task.value = task.value.copy(title = it)
                        },
                        label = { Text("Name der Task") },
                        colors = TextFieldDefaults.colors().copy(
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        ),
                        suffix = {
                            Text(
                                text = "0/50",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        singleLine = true
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = task.value.description ?: "",
                        onValueChange = {
                            task.value = task.value.copy(description = it)
                        },
                        label = { Text("Beschreibung") },
                        colors = TextFieldDefaults.colors().copy(
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        )
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        onClick = {
                            task.value = task.value.copy(
                                priority = task.value.priority + 1
                            )
                        }
                    ){
                        Text(
                            modifier = Modifier,
                            text = "Priorität erhöhen",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        onClick = {
                            openDatePicker.value = !openDatePicker.value
                        }
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Datum auswählen",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    Text(
                        text = "Fällig am: ${task.value.dueDate ?: "Kein Datum ausgewählt"}",
                    )
                    Text(
                        text = "Priorität: ${task.value.priority}",
                    )
                    Text(
                        text = "Status: ${task.value.status}",
                    )
                    if(openDatePicker.value){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ){
                            DatePickerDialog(
                                onDismissRequest = {
                                    openDatePicker.value = false
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            openDatePicker.value = false
                                        }
                                    ) {
                                        Text("Bestätigen")
                                    }
                                },
                                content = {
                                    DatePicker(
                                        state = datePickerState,
                                    )
                                }
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Button(
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("Abbrechen")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(),
                        onClick = {
                            onConfirmSelection(task.value)
                            onDismiss()
                        }
                    ) {
                        Text("Erstellen")
                    }
                }
            }
        }
    }
    
}