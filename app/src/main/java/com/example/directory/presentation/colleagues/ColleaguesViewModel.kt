package com.example.directory.presentation.colleagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colleagues.domain.PeopleResponseItem
import com.example.colleagues.interactors.GetColleagues
import com.example.common.utils.Resource
import io.reactivex.observers.DisposableSingleObserver

open class ColleaguesViewModel(
    private val getColleagues: GetColleagues
) : ViewModel() {

    val loadingError = MutableLiveData<String>()
    private val _colleagues = MutableLiveData<Resource<List<PeopleResponseItem>>>()
    val colleagues: LiveData<Resource<List<PeopleResponseItem>>> get() = _colleagues

    init {
        loadingError.value = ""
        getColleagues()
    }

    fun getColleagues() {
        getColleagues.execute(ColleaguesSubscriber())
    }


    private inner class ColleaguesSubscriber :
        DisposableSingleObserver<List<PeopleResponseItem>>() {
        override fun onSuccess(colleagues: List<PeopleResponseItem>) {
            _colleagues.value = Resource.Success(colleagues)
        }

        override fun onError(e: Throwable) {
            _colleagues.value = Resource.Error(e.message)
            loadingError.postValue(e.message)
        }

    }
}