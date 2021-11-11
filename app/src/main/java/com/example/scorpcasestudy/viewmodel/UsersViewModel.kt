package com.example.scorpcasestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scorpcasestudy.data.local.DataSource
import com.example.scorpcasestudy.data.local.Person

class UsersViewModel : ViewModel() {

    var lastResponse = MutableLiveData<List<Person>>()
    var lastError = MutableLiveData<String>()

    private val dataSource = DataSource();

    init {
        dataSource.fetch(null, completionHandler = {fetchResponse, fetchError ->
            fetchResponse?.let { lastResponse.value = it.people }
            fetchError?.let { lastError.value = it.errorDescription }
        })
    }

}