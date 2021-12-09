package gh.cloneconf.nedium.ui.parts.settings.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import gh.cloneconf.nedium.Const.THEME_KEY
import gh.cloneconf.nedium.Const.themes
import gh.cloneconf.nedium.R
import gh.cloneconf.nedium.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ThemeDialog(
    onDismiss : () -> Unit,
    onChange : () -> Unit
) {

    val scope = rememberCoroutineScope()

    val ds = LocalContext.current.dataStore

    var job : Job? = null



    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        confirmButton = {
            TextButton(onClick = { onDismiss.invoke() }) {
                Text(stringResource(R.string.cancel))
            }
        },
        text = {

            Column {
                for (i in themes.indices){
                    val theme = themes[i]

                    Box(
                        Modifier.clickable {
                            job?.cancel()

                            job = scope.launch(Dispatchers.IO) {
                                ds.edit {
                                    it[THEME_KEY] = i
                                }
                                withContext(Dispatchers.Main) {
                                    onChange.invoke()
                                }
                            }
                        }
                    ) {
                        Text(
                            stringResource(theme.nameRes),
                            Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                        )
                    }
                }
            }

        }
    )
}