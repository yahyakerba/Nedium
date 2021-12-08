package gh.cloneconf.nedium.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.google.gson.reflect.TypeToken
import gh.cloneconf.nedium.Const.USER_AGENT
import gh.cloneconf.nedium.Singleton.gson
import gh.cloneconf.nedium.Singleton.okhttp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request

@Composable
fun GoogleSuggestions(q : String) {

    var data : List<String>? by remember { mutableStateOf(null) }

    LaunchedEffect(q) {
        withContext(Dispatchers.IO) {
            val req = Request.Builder()
                .header("User-Agent", USER_AGENT)
                .url("https://google.com/complete/search?client=chrome&q=$q")
                .build()

            data = gson.fromJson<List<Any>>(
                okhttp.newCall(req).execute().body()!!.string().apply {
                    println(this)
                },
                object : TypeToken<List<Any>>() {}.type
            )[1] as List<String>

        }
    }



    data?.let { data ->

        Column {
            for (datum in data) {
                Text(text = datum)
            }
        }

    } ?: run { CircularProgressIndicator() }

}