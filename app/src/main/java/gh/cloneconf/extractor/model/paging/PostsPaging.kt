package gh.cloneconf.extractor.model.paging

import gh.cloneconf.extractor.dao.SearchPostDto
import gh.cloneconf.extractor.model.PostInfo

class PostsPaging(
    page: Int,
    val posts: List<PostInfo>,
    more: Boolean,
    val nextDao: SearchPostDto.PayloadDto.PagingDto.NextDto?
) : Paging<PostInfo>(page, posts, more)