package ows.kotlinstudy.movierankapp.repository.response


import com.google.gson.annotations.SerializedName

data class MovieWritingCommentResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("resultType")
    val resultType: String?
)