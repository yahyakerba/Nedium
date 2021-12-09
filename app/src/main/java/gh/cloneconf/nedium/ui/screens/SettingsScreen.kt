package gh.cloneconf.nedium.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gh.cloneconf.nedium.Const.THEME_KEY
import gh.cloneconf.nedium.Const.themes
import gh.cloneconf.nedium.MainActivity
import gh.cloneconf.nedium.R
import gh.cloneconf.nedium.dataStore
import gh.cloneconf.nedium.ui.parts.settings.theme.ThemeDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun SettingsScreen(navigator: DestinationsNavigator) {

    val ctx = LocalContext.current

    val ds = runBlocking { ctx.dataStore.data.first() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        }
    ) {

        LazyColumn {


            item {

                Row {

                    var showDialog by remember { mutableStateOf(false) }

                    Column(
                        Modifier.clickable { showDialog = true }
                    ) {

                        ListItem(
                            Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = { Text(stringResource(R.string.theme)) },
                            icon = { Icon(Icons.Default.Palette, null) },
                            secondaryText = { Text(stringResource(themes[ds[THEME_KEY] ?: 0].nameRes)) }
                        )
                    }


                    if (showDialog) ThemeDialog({ showDialog = false }) {
                        (ctx as MainActivity).recreate()
                    }

                }


            }


        }





    }
}


@Preview
@Composable
fun SettingsScreenPreview(){
    DestinationsNavHost(startDestination = SettingsScreenDestination)
}