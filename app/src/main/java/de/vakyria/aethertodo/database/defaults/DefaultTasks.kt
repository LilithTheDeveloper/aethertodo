package de.vakyria.aethertodo.database.defaults

import de.vakyria.aethertodo.database.types.Priority
import de.vakyria.aethertodo.database.types.Status
import sqldelight.de.vakyria.aethertodo.database.models.Task

object DefaultTasks {
    val exampleTask: Task = Task(
        id = 0,
        title = "Example Task",
        description = "This is an example task",
        status = Status.OPEN.value,
        image = null,
        dueDate = null,
        priority = Priority.LOW.value
    )


    val emptyTask: Task = Task(
        id = 0,
        title = "",
        description = "",
        status = Status.OPEN.value,
        image = null,
        dueDate = null,
        priority = Priority.LOW.value
    )
}
