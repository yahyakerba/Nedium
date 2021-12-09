package gh.cloneconf.nedium.ui.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gh.cloneconf.nedium.api.suggestions.SearchEngineBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Show suggestions from a [SearchEngineBase].
 */
@Composable
fun SuggestionsComp(
    q : String,
    engine : SearchEngineBase,
    onClick : (suggestion : String) -> Unit,
    modifier: Modifier
) {

    var suggestions : List<String>? by remember { mutableStateOf(null) }

    var err : Throwable? by remember { mutableStateOf(null) }


    LaunchedEffect(q) {
        withContext(Dispatchers.IO) {
            try {
                suggestions = engine.suggestions(q)
            }catch (e: Exception) { err = e }
        }
    }

    Box(modifier) {



        if (err == null) {
            ErrorComp(t = err!!) {}
        }else {
            suggestions?.let {


                Column {
                    for (suggestion in it) {
                        Box(Modifier.clickable { onClick.invoke(suggestion) }) {
                            Text(
                                suggestion,
                                Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }


            } ?: run {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }

    }



}