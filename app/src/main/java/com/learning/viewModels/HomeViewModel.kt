package com.learning.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.learning.IMDB
import com.learning.apis.APIService
import com.learning.apis.Const

import com.learning.model.LoginDto
import com.learning.utills.SharedPrf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.HashMap
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val retrofitInstance: APIService,
                                        private  val sharedPrf: SharedPrf)
    : ViewModel() {
    val responseContainer = MutableLiveData<LoginDto>()
    val errorMessage = MutableLiveData<String>()
    val isShowProgress = MutableLiveData<Boolean>()
    val expressionToSearch = MutableLiveData("")
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled : ${throwable.localizedMessage}")
    }

    fun login(map : HashMap<String, String>) {
        isShowProgress.value = true
        job = viewModelScope.launch {
            val str =   sharedPrf.getStoredTag(Const.USER_ID)
            val response = retrofitInstance.login(map)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e(TAG, "login: "+response.body() )

                    responseContainer.postValue(response.body())
                    //repository.insertUser(responseContainer.value?.result!!)
                    isShowProgress.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }


    }

    private fun onError(message: String) {
        errorMessage.value = message
        isShowProgress.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}