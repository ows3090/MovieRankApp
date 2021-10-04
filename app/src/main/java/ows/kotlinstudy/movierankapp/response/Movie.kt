package ows.kotlinstudy.movierankapp.response


import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @ColumnInfo(name = "actor")
    @SerializedName("actor")
    val actor: String,

    @ColumnInfo(name = "audience")
    @Nullable
    @SerializedName("audience")
    val audience: Int?,

    @ColumnInfo(name = "audience_rating")
    @Nullable
    @SerializedName("audience_rating")
    val audienceRating: Double?,

    @ColumnInfo(name = "date")
    @Nullable
    @SerializedName("date")
    val date: String?,

    @ColumnInfo(name = "director")
    @SerializedName("director")
    val director: String,

    @ColumnInfo(name = "dislike")
    @SerializedName("dislike")
    val dislike: Int,

    @ColumnInfo(name = "duration")
    @SerializedName("duration")
    val duration: Int,

    @ColumnInfo(name = "genre")
    @SerializedName("genre")
    val genre: String,

    @ColumnInfo(name = "grade")
    @SerializedName("grade")
    val grade: Int,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "image")
    @Nullable
    @SerializedName("image")
    val image: String?,

    @ColumnInfo(name = "like")
    @SerializedName("like")
    val like: Int,

    @ColumnInfo(name = "outlinks")
    @Nullable
    @SerializedName("outlinks")
    val outlinks: String?,

    @ColumnInfo(name = "photos")
    @Nullable
    @SerializedName("photos")
    val photos: String?,

    @ColumnInfo(name = "reservation_grade")
    @SerializedName("reservation_grade")
    val reservationGrade: Int,

    @ColumnInfo(name = "reservation_rate")
    @SerializedName("reservation_rate")
    val reservationRate: Double,

    @ColumnInfo(name = "reviewer_rating")
    @SerializedName("reviewer_rating")
    val reviewerRating: Double,

    @ColumnInfo(name = "synopsis")
    @SerializedName("synopsis")
    val synopsis: String,

    @ColumnInfo(name = "thumb")
    @Nullable
    @SerializedName("thumb")
    val thumb: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "user_rating")
    @SerializedName("user_rating")
    val userRating: Double,

    @ColumnInfo(name = "videos")
    @Nullable
    @SerializedName("videos")
    val videos: String?
)