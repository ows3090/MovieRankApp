package ows.kotlinstudy.movierankapp.response


import com.google.gson.annotations.SerializedName

data class MovieCommentResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: List<Comment>?,
    @SerializedName("resultType")
    val resultType: String?,
    @SerializedName("totalCount")
    val totalCount: Int?
)