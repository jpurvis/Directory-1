package com.example.rooms.iteractors

import com.example.common.executor.PostExecutionThread
import com.example.common.executor.ThreadExecutor
import com.example.rooms.domain.RoomsResponseItem
import com.example.rooms.domain.repository.IRoomsRepository
import com.example.rooms.interactors.GetRooms
import com.example.rooms.utils.RoomsFactory
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class GetRoomsTest {
    private lateinit var getRooms: GetRooms
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRoomsRepository: IRoomsRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRoomsRepository = mock()
        getRooms = GetRooms(mockRoomsRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun verify_getRooms_use_case_call_getRooms() {
        getRooms.buildUseCaseObservable(null, null)
        verify(mockRoomsRepository).getRooms()
    }

    @Test
    fun check_getRooms_use_case_complete_without_errors() {
        stubWheneverThenReturn(Single.just(RoomsFactory.makeRoomsResponse()))
        val testObserver = getRooms.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun check_getRooms_use_case_complete_with_errors() {
        val error = RoomsFactory.makeResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = getRooms.buildUseCaseObservable(null, null).test()
        testObserver.assertError(error)


    }

    @Test
    fun check_getRooms_use_case_returns_data() {
        val colleagues = RoomsFactory.makeRoomsResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        val testObserver = getRooms.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(colleagues)
    }

    @After
    fun tearDown() {
        getRooms.dispose()
    }

    private fun stubWheneverThenReturn(single: Single<List<RoomsResponseItem>>) {
        whenever(mockRoomsRepository.getRooms()).thenReturn(single)
    }

}