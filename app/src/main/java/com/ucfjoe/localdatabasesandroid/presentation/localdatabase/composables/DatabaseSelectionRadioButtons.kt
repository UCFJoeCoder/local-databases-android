package com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.ActiveDatabaseType
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.LocalDatabaseEvents
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.LocalDatabaseState

@Composable
fun DatabaseSelectionRadioButtons(
    state: LocalDatabaseState,
    onEvent: (LocalDatabaseEvents) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .fillMaxWidth(.33f)
                .clickable { onEvent(LocalDatabaseEvents.OnActiveDatabaseChanged(ActiveDatabaseType.ROOM)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.activeDatabaseType == ActiveDatabaseType.ROOM,
                onClick = { onEvent(LocalDatabaseEvents.OnActiveDatabaseChanged(ActiveDatabaseType.ROOM)) }
            )
            Text("Room", Modifier.padding(end = 6.dp), color = MaterialTheme.colorScheme.onSurface)
        }
        Row(
            Modifier
                .fillMaxWidth(.5f)
                .clickable { onEvent(LocalDatabaseEvents.OnActiveDatabaseChanged(ActiveDatabaseType.BOTH)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.activeDatabaseType == ActiveDatabaseType.BOTH,
                onClick = { onEvent(LocalDatabaseEvents.OnActiveDatabaseChanged(ActiveDatabaseType.BOTH)) }
            )
            Text("Both", Modifier.padding(end = 6.dp), color = MaterialTheme.colorScheme.onSurface)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .clickable { onEvent(LocalDatabaseEvents.OnActiveDatabaseChanged(ActiveDatabaseType.SQL_DELIGHT)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.activeDatabaseType == ActiveDatabaseType.SQL_DELIGHT,
                onClick = { onEvent(LocalDatabaseEvents.OnActiveDatabaseChanged(ActiveDatabaseType.SQL_DELIGHT)) }
            )
            Text("SqlDelight", color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatabaseSelectionRadioButtons() {
    DatabaseSelectionRadioButtons(
        state = LocalDatabaseState(),
        onEvent = {}
    )
}