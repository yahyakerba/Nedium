package gh.cloneconf.nedium.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gh.cloneconf.nedium.BuildConfig
import gh.cloneconf.nedium.R

@Destination(start = true)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {


    Scaffold {


        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Text(stringResource(R.string.app_name), fontSize = 50.sp)

            Text("Share Medium article to start reading.", Modifier.padding(0.dp, 20.dp))

            Text(BuildConfig.VERSION_NAME)

        }

    }
}