package ows.kotlinstudy.movierankapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ows.kotlinstudy.movierankapp.Constants.WRITEER
import ows.kotlinstudy.movierankapp.repository.MovieRepository
import javax.inject.Inject

class WritingViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val ratingMutableLiveData = MutableLiveData<Float>()
    val commentMutableLiveData = MutableLiveData<String>()

    private val finishMutableLiveData = MutableLiveData<Boolean>()
    val finishLiveData: LiveData<Boolean>
        get() = finishMutableLiveData

    fun requestAddcomment(id: Int) {
        finishMutableLiveData.value = false
        viewModelScope.launch {
            val response = repository.requestWriteComment(
                id,
                WRITEER,
                ratingMutableLiveData.value ?: 0f,
                commentMutableLiveData.value ?: ""
            )

            if (response.code == 1) {
                response.data?.let {
                    finishMutableLiveData.value = true
                }
            }
        }
    }

}