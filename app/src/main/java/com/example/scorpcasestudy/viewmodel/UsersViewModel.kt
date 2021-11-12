package com.example.scorpcasestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scorpcasestudy.data.local.DataSource
import com.example.scorpcasestudy.data.local.Person

class UsersViewModel : ViewModel() {

    var lastResponse = MutableLiveData<List<Person>?>()
    var lastError = MutableLiveData<String>()

    private val dataSource = DataSource();

    init {
        fetchData()
    }

    fun fetchData() {
        dataSource.fetch(null, completionHandler = {fetchResponse, fetchError ->
            lastResponse.value = fetchResponse?.people
            lastError.value = fetchError?.errorDescription
        })
    }


}