package ows.kotlinstudy.movierankapp.response


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Comment(
    @ColumnInfo(name = "contents")
    @SerializedName("contents")
    val contents: String,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "movieId")
    @SerializedName("movieId")
    val movieId: Int,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    val rating: Double,

    @ColumnInfo(name = "recommend")
    @SerializedName("recommend")
    val recommend: Int,

    @ColumnInfo(name = "time")
    @SerializedName("time")
    val time: String,

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    val timestamp: Int,

    @ColumnInfo(name = "writer")
    @SerializedName("writer")
    val writer: String,

    @ColumnInfo(name = "writer_image")
    @SerializedName("writer_image")
    val writerImage: String?
)