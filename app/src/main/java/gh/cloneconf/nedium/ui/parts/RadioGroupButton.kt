package gh.cloneconf.nedium.ui.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroupButton(){
    var selected by remember { mutableStateOf("Male") }
    Row {
        RadioButton(selected = selected == "Male", onClick = { selected = "Male" })
        Text(
            text = "Male",
            modifier = Modifier.clickable(onClick = { selected = "Male" }).padding(start = 4.dp)
        )
    }
}