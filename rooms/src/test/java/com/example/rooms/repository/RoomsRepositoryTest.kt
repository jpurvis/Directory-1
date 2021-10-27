package com.example.rooms.repository

import com.example.rooms.data.datasource.IRoomsDataSource
import com.example.rooms.data.repository.RoomsRepository
import com.example.rooms.utils.RoomsFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class RoomsRepositoryTest {
    private lateinit var roomsRepository: RoomsRepository
    private lateinit var mockDataSource: IRoomsDataSource

    @Before
    fun setUp() {
        mockDataSource = mock()
        roomsRepository = RoomsRepository(mockDataSource)
    }

    @Test
    fun verify_RoomsRepository_call_getRoomsAsync() {
        roomsRepository.getRooms()
        verify(mockDataSource).getRoomsAsync()
    }

    @Test
    fun rooms_repository_complete_without_errors() {
        val rooms = RoomsFactory.makeRoomsResponse()
        Mockito.`when`(mockDataSource.getRoomsAsync()).thenReturn(Single.just(rooms))
        val testObserver = roomsRepository.getRooms().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun rooms_repository_complete_with_errors() {
        val error = RoomsFactory.makeResponseError()
        Mockito.`when`(mockDataSource.getRoomsAsync()).thenReturn(Single.error(error))
        val testObserver = roomsRepository.getRooms().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun colleagues_repository_returns_data() {
        val rooms = RoomsFactory.makeRoomsResponse()
        Mockito.`when`(mockDataSource.getRoomsAsync()).thenReturn(Single.just(rooms))
        val testObserver = roomsRepository.getRooms().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(rooms)
    }


}