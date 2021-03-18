package com.harish.tfl.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harish.tfl.data.RoadStatusModel

@Composable
fun SuccessStatusView(status: String, roadStatusStatusModel: RoadStatusModel, modifier: Modifier) {
  Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(status, style = MaterialTheme.typography.h4, modifier = Modifier.padding(bottom = 4.dp))
        Text("• ${roadStatusStatusModel.displayName}")
        Text("• ${roadStatusStatusModel.statusSeverity}")
        Text("• ${roadStatusStatusModel.statusSeverityDescription}")
      }
    }
  }
}


