package ows.kotlinstudy.movierankapp.response


import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("contents")
    val contents: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("movieId")
    val movieId: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("recommend")
    val recommend: Int?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("writer")
    val writer: String?,
    @SerializedName("writer_image")
    val writerImage: Any?
)