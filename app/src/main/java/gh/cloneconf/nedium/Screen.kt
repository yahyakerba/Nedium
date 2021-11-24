package gh.cloneconf.nedium


const val ARG_ID = "id"

sealed class Screen(val route : String) {

    object Home : Screen("home_screen")

    object Search : Screen("search_screen")

    object Post : Screen("post_screen/{$ARG_ID}") {
        fun byId(id : String) = "$route/$id"
    }

    object User : Screen("user_screen/{$ARG_ID}") {
        fun byId(id : String) = "$route/$id"
    }

}