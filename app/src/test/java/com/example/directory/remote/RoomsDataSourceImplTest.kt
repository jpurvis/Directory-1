package com.example.directory.remote

import com.example.directory.framework.ApiService
import com.example.directory.framework.remote.RoomsDataSourceImpl
import com.example.directory.utils.RoomsFactory
import com.example.rooms.domain.RoomsResponseItem
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class RoomsDataSourceImplTest {

    private lateinit var mockApiService: ApiService
    private lateinit var roomsDataSourceImpl: RoomsDataSourceImpl

    @Before
    fun setUp() {
        mockApiService = mock()
        roomsDataSourceImpl = RoomsDataSourceImpl(mockApiService)
    }

    @Test
    fun verify_getRoomsAsync_called() {
        roomsDataSourceImpl.getRoomsAsync()
        verify(mockApiService).getRooms()
    }

    @Test
    fun getRoomsAsync_complete_without_errors() {
        val rooms = RoomsFactory.makeRoomsResponse()
        stubWheneverThenReturn(Single.just(rooms))
        val testObserver = roomsDataSourceImpl.getRoomsAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getRoomsAsync_with_errors() {
        val error = RoomsFactory.makeResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = roomsDataSourceImpl.getRoomsAsync().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun getRoomsAsync_returns_data() {
        val rooms = RoomsFactory.makeRoomsResponse()
        stubWheneverThenReturn(Single.just(rooms))
        val testObserver = roomsDataSourceImpl.getRoomsAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(rooms)
    }

    private fun stubWheneverThenReturn(single: Single<List<RoomsResponseItem>>) {
        whenever(mockApiService.getRooms()).thenReturn(single)
    }
}