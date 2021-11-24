package gh.cloneconf.nedium.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import gh.cloneconf.nedium.ARG_ID
import gh.cloneconf.nedium.Screen
import gh.cloneconf.nedium.screens.HomeScreen
import gh.cloneconf.nedium.screens.PostScreen
import gh.cloneconf.nedium.screens.SearchScreen
import gh.cloneconf.nedium.screens.UserScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    start: String
) {

    NavHost(navController = navController, startDestination = start) {

        composable(Screen.Home.route) { HomeScreen(navController) }

        composable(Screen.Search.route) { SearchScreen(navController = navController)}

        composable(
            Screen.Post.route,
            arguments = listOf(
                navArgument(ARG_ID) { type = NavType.StringType }
            )
        ) {
            PostScreen(navController, it.arguments?.getString(ARG_ID)!!)
        }

        composable(
            Screen.User.route,
            arguments = listOf(
                navArgument(ARG_ID) { type = NavType.StringType }
            )
        ) {
            UserScreen(navController, it.arguments?.getString(ARG_ID)!!)
        }

    }

}