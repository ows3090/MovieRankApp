package ows.kotlinstudy.movierankapp.repository.response


import com.google.gson.annotations.SerializedName
import ows.kotlinstudy.movierankapp.repository.model.SimpleMovie

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