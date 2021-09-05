package ows.kotlinstudy.movierankapp.response


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("result")
    val result: List<SimpleMovie> = emptyList(),
    @SerializedName("resultType")
    val resultType: String = ""
)