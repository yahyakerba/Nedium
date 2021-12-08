package gh.cloneconf.nedium.ui.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gh.cloneconf.nedium.R

@Composable
fun ErrorComp(
    t: Throwable,
    tryAgain : () -> Unit
) {

    var visible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            Modifier.clickable { visible = !visible },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(Modifier.weight(1f).padding(5.dp)) {
                Text(
                    stringResource(R.string.unexpected_error),
                    style = MaterialTheme.typography.h1
                )

                (t.localizedMessage ?: t.message)?.also { Text(it) }
            }


            Icon(
                if (visible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                null,
                modifier = Modifier.padding(10.dp)
            )

        }

        if (visible) Text(t.stackTraceToString(), Modifier.padding(20.dp))

        Button(
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 20.dp),
            onClick = { tryAgain.invoke() }) {
            Text(stringResource(R.string.try_again))
        }

    }


}