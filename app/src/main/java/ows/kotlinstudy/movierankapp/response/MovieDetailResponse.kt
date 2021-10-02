package ows.kotlinstudy.movierankapp.response


import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("actor")
    val actor: String?,
    @SerializedName("audience")
    val audience: Int?,
    @SerializedName("audience_rating")
    val audienceRating: Double?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("dislike")
    val dislike: Int?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("genre")
    val genre: String?,
    @SerializedName("grade")
    val grade: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("like")
    val like: Int?,
    @SerializedName("outlinks")
    val outlinks: Any?,
    @SerializedName("photos")
    val photos: String?,
    @SerializedName("reservation_grade")
    val reservationGrade: Int?,
    @SerializedName("reservation_rate")
    val reservationRate: Double?,
    @SerializedName("reviewer_rating")
    val reviewerRating: Double?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("thumb")
    val thumb: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("user_rating")
    val userRating: Double?,
    @SerializedName("videos")
    val videos: String?
)