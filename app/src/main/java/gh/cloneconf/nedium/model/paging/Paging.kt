package gh.cloneconf.nedium.model.paging

open class Paging<T> (
    val items : List<T>,
    val more : Boolean = false,
)