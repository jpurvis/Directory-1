package com.example.colleagues.interactors

import com.example.colleagues.domain.PeopleResponseItem
import com.example.colleagues.domain.repository.IPeopleRepository
import com.example.colleagues.interactors.GetColleagues
import com.example.colleagues.utils.ColleaguesFactory
import com.example.common.executor.PostExecutionThread
import com.example.common.executor.ThreadExecutor
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
class GetColleaguesTest {
    private lateinit var getColleagues: GetColleagues
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockPeopleRepository: IPeopleRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockPeopleRepository = mock()
        getColleagues =
            GetColleagues(mockPeopleRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun verify_getColleagues_use_case_call_getColleagues() {
        getColleagues.buildUseCaseObservable(null, null)
        verify(mockPeopleRepository).getColleagues()
    }

    @Test
    fun check_getColleagues_use_case_complete_without_errors() {
        stubWheneverThenReturn(Single.just(ColleaguesFactory.makeColleaguesResponse()))
        val testObserver = getColleagues.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun check_getColleagues_use_case_complete_with_errors() {
        val error = ColleaguesFactory.makeResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = getColleagues.buildUseCaseObservable(null, null).test()
        testObserver.assertError(error)


    }

    @Test
    fun check_getColleagues_use_case_returns_data() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        val testObserver = getColleagues.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(colleagues)
    }

    @After
    fun tearDown() {
        getColleagues.dispose()
    }

    private fun stubWheneverThenReturn(single: Single<List<PeopleResponseItem>>) {
        whenever(mockPeopleRepository.getColleagues()).thenReturn(single)
    }


}