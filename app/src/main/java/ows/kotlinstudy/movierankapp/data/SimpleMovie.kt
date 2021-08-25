package ows.kotlinstudy.movierankapp.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SimpleMovie(
    @SerializedName("audience_rating")
    @ColumnInfo(name = "audienRating")
    val audienceRating: Double,

    @SerializedName("date")
    @ColumnInfo(name = "date")
    val date: String,

    @SerializedName("grade")
    @ColumnInfo(name = "grade")
    val grade: Int,

    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String,

    @SerializedName("reservation_grade")
    @ColumnInfo(name = "reservationGrade")
    val reservationGrade: Int,

    @SerializedName("reservation_rate")
    @ColumnInfo(name = "reservation_rate")
    val reservationRate: Double,

    @SerializedName("reviewer_rating")
    @ColumnInfo(name = "reviewer_rating")
    val reviewerRating: Double,

    @SerializedName("thumb")
    @ColumnInfo(name = "thumb")
    val thumb: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("title_eng")
    @ColumnInfo(name = "title_eng")
    val titleEng: String,

    @SerializedName("user_rating")
    @ColumnInfo(name = "user_rating")
    val userRating: Double
)