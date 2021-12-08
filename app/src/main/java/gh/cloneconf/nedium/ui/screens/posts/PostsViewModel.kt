package gh.cloneconf.nedium.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import gh.cloneconf.nedium.api.dao.SearchPostDto
import gh.cloneconf.nedium.model.PostInfo

class PostsViewModel : ViewModel() {


    var a : Pager<SearchPostDto.PayloadDto.PagingDto.NextDto, PostInfo>? = null



}