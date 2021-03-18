package com.harish.tfl.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun RoadNameInputView(
    label: String,
    roadLabel: String,
    roadStatusServiceCall: (roadName: String) -> Unit,
    showLoading: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(label)

            val textState = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = textState.value,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(roadLabel) },
                onValueChange = {
                    textState.value = it
                    roadStatusServiceCall(textState.value.text)
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )

            Spacer(Modifier.size(20.dp))
            CircularProgressBar(isDisplayed = showLoading)
        }
    }
}