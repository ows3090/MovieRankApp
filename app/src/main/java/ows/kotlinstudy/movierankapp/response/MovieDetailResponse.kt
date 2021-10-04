package ows.kotlinstudy.movierankapp.response


import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("result")
    val result: List<Movie>?,
    @SerializedName("resultType")
    val resultType: String = ""
)