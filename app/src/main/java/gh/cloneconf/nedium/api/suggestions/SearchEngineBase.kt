package gh.cloneconf.nedium.api.suggestions

/**
 * Base class for suggestion provider.
 */
// TODO: 08.12.21 Code generate information of all search engines available.
abstract class SearchEngineBase(
    val name : String,
) {

    abstract fun suggestions(q : String) : List<String>

}