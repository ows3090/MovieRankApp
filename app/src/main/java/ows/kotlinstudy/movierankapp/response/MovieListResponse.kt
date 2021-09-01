package ows.kotlinstudy.movierankapp.response


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: List<SimpleMovie>,
    @SerializedName("resultType")
    val resultType: String
)