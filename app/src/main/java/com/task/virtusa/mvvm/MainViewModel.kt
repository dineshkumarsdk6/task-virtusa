package com.task.virtusa.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.task.virtusa.model.GetData
import com.task.virtusa.retrofit.RetrofitApiInterFace
import com.task.virtusa.retrofit.RetrofitInstance
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val getData = MutableLiveData<GetData>()
    var loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getData(id : String) {
        loading.value = true
        val apiInterface = RetrofitInstance.getRetrofitInstance()!!.create(RetrofitApiInterFace::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface.getData(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val result = gson.toJson(response.body())
                    println("-- result output : $result")
                    getData.postValue(response.body())
                    loading.value = false
                } else {
                    error.postValue(response.message())
                    println("-- result error : ${response.message()}")
                    loading.value = false
                }
            }
        }
    }
}