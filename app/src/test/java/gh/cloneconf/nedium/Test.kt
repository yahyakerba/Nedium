package gh.cloneconf.nedium

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.medium.PostQuery
import gh.cloneconf.nedium.Singleton.apollo

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        apollo.query(PostQuery("83748ae17dfc")).enqueue(object : ApolloCall.Callback<PostQuery.Data>() {
            override fun onResponse(response: Response<PostQuery.Data>) {
                println(response.data!!.post()!!.creator())
            }

            override fun onFailure(e: ApolloException) {
                println(e)
            }

        })
    }
}