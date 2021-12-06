package gh.cloneconf.extractor.model.paging

open class Paging<T> (
    val page : Int,
    val items : List<T>,
    val more : Boolean
)