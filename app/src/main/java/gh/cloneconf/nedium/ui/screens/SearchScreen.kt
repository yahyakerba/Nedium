package gh.cloneconf.nedium.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.PostScreenDestination
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gh.cloneconf.nedium.R
import gh.cloneconf.nedium.api.suggestions.GoogleEngine
import gh.cloneconf.nedium.ui.parts.SuggestionsComp
import gh.cloneconf.nedium.ui.screens.posts.PostResults


/**
 * Search screen.
 * Search for posts and show suggestions from [gh.cloneconf.nedium.api.suggestions.SearchEngineBase].
 */
@Destination(
    deepLinks = [
        DeepLink(uriPattern = "https://medium.com/search"),
        DeepLink(uriPattern = "https://medium.com/search?q={defaultQ}"),
    ]
)
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    navController: NavController,
    defaultQ: String = "",
) {


    val focusManager = LocalFocusManager.current


    Scaffold {
        Column {

            var q by rememberSaveable { mutableStateOf(defaultQ) }

            val focusRequester = remember { FocusRequester() }

            var doneShowing by rememberSaveable { mutableStateOf(false) }

            var inputMode by rememberSaveable { mutableStateOf(true) }




            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.drawBehind {
                    // see: https://stackoverflow.com/questions/68592618/how-to-add-border-on-bottom-only-in-jetpack-compose
                    val strokeWidth = 2
                    val y = size.height - strokeWidth / 2

                    drawLine(
                        Color.Gray,
                        Offset(0f, y),
                        Offset(size.width, y),
                        2f
                    )
                }
            ) {


                OutlinedTextField(
                    placeholder = { Text(stringResource(R.string.search_for_articles)) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp, 3.dp, 10.dp, 3.dp)
                        .focusRequester(focusRequester),
                    value = q,
                    onValueChange = { q = it; inputMode = true },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = false,
                        capitalization = KeyboardCapitalization.None,
                        imeAction = ImeAction.Default
                    ),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { navigator.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    trailingIcon = {

                        if (q.isNotEmpty()) IconButton(
                            onClick = {
                                q = ""
                                focusRequester.requestFocus()
                            }
                        ) {
                            Icon(Icons.Default.Close, null)
                        }

                    },
                )


                // Only show the keyboard once.
                LaunchedEffect(doneShowing) {
                    if (!doneShowing) {
                        focusRequester.requestFocus()
                        doneShowing = true
                    }
                }

            }


            if (inputMode) {

                SuggestionsComp(q, GoogleEngine(), modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), onClick = {
                    q = it
                    inputMode = false
                    focusManager.clearFocus(true)
                })

            } else {
                PostResults(q = q) {
                    navigator.navigate(PostScreenDestination(it))
                }
            }

        }
    }

}