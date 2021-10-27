package com.example.directory.presentation.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.utils.Resource
import com.example.rooms.domain.RoomsResponseItem
import com.example.rooms.interactors.GetRooms
import io.reactivex.observers.DisposableSingleObserver

class RoomsViewModel(private val getRooms: GetRooms) : ViewModel() {

    private val _rooms = MutableLiveData<Resource<List<RoomsResponseItem>>>()
    val rooms: LiveData<Resource<List<RoomsResponseItem>>> get() = _rooms
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress

    init {
        getRooms()
        _progress.value = true
    }

    fun getRooms() {
        getRooms.execute(RoomsSubscriber())
    }

    private inner class RoomsSubscriber : DisposableSingleObserver<List<RoomsResponseItem>>() {
        override fun onSuccess(rooms: List<RoomsResponseItem>) {
            _rooms.value = Resource.Success(rooms)
            _progress.value = false
        }

        override fun onError(e: Throwable) {
            _rooms.value = Resource.Error(e.message)
            _progress.value = false
        }
    }
}