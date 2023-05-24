package com.learning.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.IMDB
import com.learning.apis.Const.Companion.API_KEY
import com.learning.apis.APIService
import com.learning.apis.Const.Companion.TOKEN
import com.learning.apis.Const.Companion.USER_ID
import com.learning.utills.SharedPrf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val retrofitInstance: APIService , private  val sharedPrf: SharedPrf) : ViewModel() {
   val responseContainer = MutableLiveData<IMDB>()
   val errorMessage = MutableLiveData<String>()
   val isShowProgress = MutableLiveData<Boolean>()
   val expressionToSearch = MutableLiveData("")

   var job: Job? = null
   val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
       onError("Exception handled : ${throwable.localizedMessage}")
   }

   fun getMoviesFromAPI(expression : String) {
       isShowProgress.value = true
       job = viewModelScope.launch {
         val str =   sharedPrf.getStoredTag(USER_ID)
         val token =   sharedPrf.getStoredTag(TOKEN)
           val map :HashMap<String, String >  = HashMap()
           map["user_id"] = str
           map["token"] = token
           val response = retrofitInstance.get_managerList(map)
           withContext(Dispatchers.Main) {
               if (response.isSuccessful) {
                   responseContainer.postValue(response.body())
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