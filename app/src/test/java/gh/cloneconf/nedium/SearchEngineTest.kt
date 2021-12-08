package gh.cloneconf.nedium
import gh.cloneconf.nedium.api.suggestions.DuckduckgoEngine
import gh.cloneconf.nedium.api.suggestions.GoogleEngine
import gh.cloneconf.nedium.api.suggestions.QwantEngine
import gh.cloneconf.nedium.api.suggestions.SearchEngineBase
import org.junit.Test

class SearchEngineTest {

    companion object {
        const val Q = "Hello"
    }

    @Test
    fun googleSuggestions() {
        testSuggestions(GoogleEngine())
    }

    @Test
    fun duckduckgoSuggestions() {
        testSuggestions(DuckduckgoEngine())
    }

    @Test
    fun qwantSuggestions() {
        testSuggestions(QwantEngine())
    }


    fun testSuggestions(engine: SearchEngineBase, q : String = Q) {
        println("$ Searching suggestions for $q on '${engine.name}' engine:")
        for (suggestion in engine.suggestions(Q)) {
            println("- $suggestion")
        }
    }

}