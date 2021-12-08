package gh.cloneconf.nedium.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.AboutScreenDestination
import com.ramcosta.composedestinations.SearchScreenDestination
import com.ramcosta.composedestinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gh.cloneconf.nedium.BuildConfig
import gh.cloneconf.nedium.R
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Column {

                LazyColumn(
                    Modifier.weight(1f)
                ) {

                }

                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            navigator.navigate(SettingsScreenDestination)
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Settings,
                        null,
                        Modifier.padding(10.dp))
                    Text(
                        stringResource(R.string.settings),
                        Modifier.weight(1f)
                    )
                }

                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            navigator.navigate(AboutScreenDestination)
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Info,
                        null,
                        Modifier.padding(10.dp))
                    Text(
                        stringResource(R.string.about),
                        Modifier.weight(1f)
                    )
                }

            }
        },
    ) {

        Column {


            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        Modifier.clickable(MutableInteractionSource(), null) {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Menu,
                            null,
                            modifier = Modifier.padding(20.dp)
                        )
                    }


                    Box(
                        Modifier.weight(1f)
                            .clickable(MutableInteractionSource(), null) {
                                navigator.navigate(SearchScreenDestination())
                            }
                    ) {
                        Text(
                            stringResource(R.string.search_for_articles),
                            Modifier.padding(20.dp)
                        )
                    }


                }
            }


            Column(
                Modifier
                    .weight(1f)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(stringResource(R.string.welcome), fontSize = 50.sp)

                Text(
                    "Start searching, or share your medium article link to start reading :)",
                    Modifier.padding(0.dp, 20.dp)
                )

                Text(BuildConfig.VERSION_NAME)

            }

        }

    }
}