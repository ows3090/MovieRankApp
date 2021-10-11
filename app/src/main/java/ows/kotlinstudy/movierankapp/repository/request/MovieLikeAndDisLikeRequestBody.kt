package ows.kotlinstudy.movierankapp.repository.request

data class MovieLikeAndDisLikeRequestBody(
    val id : String,
    val likeyn : String? = null,
    val dislikeyn : String? = null
)
