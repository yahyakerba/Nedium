package gh.cloneconf.nedium.screens

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.PostScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gh.cloneconf.nedium.scrap.search.PostResults

@Destination
@Composable
fun SearchScreen(navigator: DestinationsNavigator) {
    Scaffold {

        var q by rememberSaveable { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }

        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }


        Column {
            OutlinedTextField(
                value = TextFieldValue(
                    text = q,
                    selection = TextRange(q.length)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .focusRequester(focusRequester),
                onValueChange = { q = it.text },
            )


            PostResults(q) {
                navigator.navigate(PostScreenDestination(it))
            }
        }


    }

}

