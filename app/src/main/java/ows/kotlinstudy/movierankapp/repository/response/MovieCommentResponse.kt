package ows.kotlinstudy.movierankapp.repository.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ows.kotlinstudy.movierankapp.repository.model.Comment

@Parcelize
data class MovieCommentResponse(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("result")
    val result: List<Comment>?,

    @SerializedName("resultType")
    val resultType: String = "",

    @SerializedName("totalCount")
    val totalCount: Int = 0
) : Parcelable