package gh.cloneconf.nedium

import gh.cloneconf.nedium.scrap.search.MediumSearch
import gh.cloneconf.nedium.scrap.search.dao.SearchPostDao

object Test {


    var nextObj : SearchPostDao.PayloadDao.PagingDao.NextDao? = null

    @JvmStatic
    fun main(args: Array<String>) {
        val q = "Hello"

        while (true) {
            MediumSearch.posts(q, nextObj).apply {
                this@Test.nextObj = nextObj

                items.forEach {
                    println(it)
                }
            }

            Thread.sleep(1000)
        }
    }
}