package com.example.directory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.example.colleagues.domain.PeopleResponseItem
import com.example.common.utils.Resource
import com.example.directory.presentation.rooms.RoomsViewModel
import com.example.directory.utils.RoomsFactory
import com.example.rooms.domain.RoomsResponseItem
import com.example.rooms.interactors.GetRooms
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class RoomsViewModelTest {
    private lateinit var mockGetRoomsUseCase: GetRooms
    private lateinit var roomsViewModel: RoomsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockGetRoomsUseCase = mock()
        roomsViewModel = RoomsViewModel(mockGetRoomsUseCase)
    }

    @Test
    fun test_loading_is_emitted() {
        val rooms = RoomsFactory.makeRoomsResponse()
        stubWheneverThenReturn(Single.just(rooms))
        roomsViewModel.getRooms()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(roomsViewModel.rooms) { result ->
            Assert.assertTrue(result is Resource.Loading)
        }
    }

    @Test
    fun test_error_is_emitted() {
        stubWheneverThenReturn(Single.error(RuntimeException()))
        roomsViewModel.getRooms()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(roomsViewModel.rooms) { result ->
            Assert.assertTrue(result is Resource.Error)
        }
    }

    @Test
    fun test_data_is_emitted() {
        val rooms = RoomsFactory.makeRoomsResponse()
        stubWheneverThenReturn(Single.just(rooms))
        roomsViewModel.getRooms()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(roomsViewModel.rooms) { result ->
            Assert.assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun test_data_emitted_is_listOf_rooms() {
        val rooms = RoomsFactory.makeRoomsResponse()
        stubWheneverThenReturn(Single.just(rooms))
        roomsViewModel.getRooms()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(roomsViewModel.rooms) { result ->
            when (result) {
                is Resource.Success -> {
                    val result = result.result
                    Assert.assertEquals(rooms, result)
                }
            }
        }
    }

    private fun stubWheneverThenReturn(single: Single<List<RoomsResponseItem>>) {
        whenever(mockGetRoomsUseCase.buildUseCaseObservable(any(), any())).thenReturn(single)
    }
}