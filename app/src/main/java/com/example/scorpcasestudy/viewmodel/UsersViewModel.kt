package com.example.scorpcasestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scorpcasestudy.data.local.DataSource
import com.example.scorpcasestudy.data.local.Person

class UsersViewModel : ViewModel() {

    var lastResponse = MutableLiveData<List<Person>?>()
    var lastError = MutableLiveData<String?>()
    var pageReference : String? = null
    var paginationProgress = MutableLiveData<Boolean>(false)

    private val dataSource = DataSource();

    init {
        fetchData(true)
    }

    fun fetchData(fromScratch : Boolean = false) {
        paginationProgress.value = true
        lastError.value = null
        if(fromScratch) {
            pageReference = null
            lastResponse.value = null
        }
        dataSource.fetch(pageReference, completionHandler = {fetchResponse, fetchError ->
            fetchResponse?.let { response ->
                lastError.value = null
                val personList = lastResponse.value?.toMutableList() ?: mutableListOf()
                personList.addAll(response.people)
                val finalList = personList.distinct()
                when (finalList.size) {
                    0 -> lastError.value = "No item in the list, please try again"
                    else -> lastResponse.value = finalList
                }
                pageReference = response.next
            }
            fetchError?.let { error ->
                lastError.value = error.errorDescription
            }
            paginationProgress.value = false
        })
    }

    fun refreshList() {
        lastResponse.value = lastResponse.value?.sortedBy { it.id }
    }

}