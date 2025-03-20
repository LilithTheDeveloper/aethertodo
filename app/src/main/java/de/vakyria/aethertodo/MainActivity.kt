package de.vakyria.aethertodo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.vakyria.aethertodo.database.defaults.DefaultTasks
import de.vakyria.aethertodo.ui.components.ModalCreateTask
import de.vakyria.aethertodo.ui.components.TaskElement
import de.vakyria.aethertodo.ui.theme.AetherToDoTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val creationModal = remember { mutableStateOf(false) }

            AetherToDoTheme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                creationModal.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Task"
                            )
                        }

                    },
                    modifier = Modifier.fillMaxSize()

                ){
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Heute",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        TaskElement(
                            task = DefaultTasks.exampleTask
                        )
                        TaskElement(
                            task = DefaultTasks.exampleTask
                        )
                        TaskElement(
                            task = DefaultTasks.exampleTask
                        )
                        TaskElement(
                            task = DefaultTasks.exampleTask
                        )
                        TaskElement(
                            task = DefaultTasks.exampleTask
                        )
                    }
                    if(creationModal.value){
                        ModalCreateTask(
                            onDismiss = {
                                creationModal.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}
