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

    private val _colleagues = MutableLiveData<Resource<List<PeopleResponseItem>>>()
    val colleagues: LiveData<Resource<List<PeopleResponseItem>>> get() = _colleagues
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress

    init {
        getColleagues()
        _progress.value = true
    }

    fun getColleagues() {
        getColleagues.execute(ColleaguesSubscriber())
    }


    private inner class ColleaguesSubscriber :
        DisposableSingleObserver<List<PeopleResponseItem>>() {
        override fun onSuccess(colleagues: List<PeopleResponseItem>) {
            _colleagues.value = Resource.Success(colleagues)
            _progress.value = false
        }

        override fun onError(e: Throwable) {
            _colleagues.value = Resource.Error(e.message)
            _progress.value = false
        }

    }
}