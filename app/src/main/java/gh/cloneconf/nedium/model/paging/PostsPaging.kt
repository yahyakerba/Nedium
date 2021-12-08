package gh.cloneconf.nedium.model.paging

import gh.cloneconf.nedium.api.dao.SearchPostDto
import gh.cloneconf.nedium.model.PostInfo

class PostsPaging(
    page: Int,
    val posts: List<PostInfo>,
    more: Boolean,
    val nextDao: SearchPostDto.PayloadDto.PagingDto.NextDto?
) : Paging<PostInfo>(page, posts, more)