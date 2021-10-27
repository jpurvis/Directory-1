package com.example.directory.remote

import com.example.colleagues.domain.PeopleResponseItem
import com.example.directory.framework.ApiService
import com.example.directory.framework.remote.PeopleDataSourceImpl
import com.example.directory.utils.ColleaguesFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class PeopleDataSourceImplTest {

    private lateinit var mockApiService: ApiService
    private lateinit var peopleDataSourceImpl: PeopleDataSourceImpl

    @Before
    fun setUp() {
        mockApiService = mock()
        peopleDataSourceImpl = PeopleDataSourceImpl(mockApiService)
    }

    @Test
    fun verify_getColleagueAsync_called() {
        peopleDataSourceImpl.getColleaguesAsync()
        verify(mockApiService).getColleagues()
    }

    @Test
    fun getColleaguesAsync_complete_without_errors() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        val testObserver = peopleDataSourceImpl.getColleaguesAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getColleaguesAsync_with_errors() {
        val error = ColleaguesFactory.makeResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = peopleDataSourceImpl.getColleaguesAsync().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun getColleaguesAsync_returns_data() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        val testObserver = peopleDataSourceImpl.getColleaguesAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(colleagues)
    }

    private fun stubWheneverThenReturn(single: Single<List<PeopleResponseItem>>) {
        whenever(mockApiService.getColleagues()).thenReturn(single)
    }

}