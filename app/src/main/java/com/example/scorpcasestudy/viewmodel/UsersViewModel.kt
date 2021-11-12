package com.example.scorpcasestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scorpcasestudy.data.local.DataSource
import com.example.scorpcasestudy.data.local.Person

class UsersViewModel : ViewModel() {

    var lastResponse = MutableLiveData<List<Person>?>()
    var lastError = MutableLiveData<String>()
    var pageReference : String? = null
    var paginationProgress = MutableLiveData<Boolean>(false)

    private val dataSource = DataSource();

    init {
        fetchData(true)
    }

    fun fetchData(fromScratch : Boolean = false) {
        if(fromScratch) {
            pageReference = null
            lastResponse.value = null
        } else {
            paginationProgress.value = true
        }
        dataSource.fetch(pageReference, completionHandler = {fetchResponse, fetchError ->
            val personList = lastResponse.value?.toMutableList() ?: mutableListOf()
            fetchResponse?.people?.let { personList.addAll(it) }
            lastResponse.value = personList
            pageReference = fetchResponse?.next
            lastError.value = fetchError?.errorDescription
            paginationProgress.value = false
        })
    }


}