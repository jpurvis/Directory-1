package com.example.colleagues.repository

import com.example.colleagues.data.datasource.IPeopleDataSource
import com.example.colleagues.data.repository.PeopleRepository
import com.example.colleagues.utils.ColleaguesFactory
import io.reactivex.Single

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class PeopleRepositoryTest {
    private lateinit var peopleRepository: PeopleRepository
    private lateinit var mockDataSource: IPeopleDataSource

    @Before
    fun setUp() {
        mockDataSource = mock()
        peopleRepository = PeopleRepository(mockDataSource)
    }

    @Test
    fun verify_PeopleRepository_call_getColleaguesAsync() {
        peopleRepository.getColleagues()
        verify(mockDataSource).getColleaguesAsync()
    }

    @Test
    fun colleagues_repositoryComplete_without_errors() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        Mockito.`when`(mockDataSource.getColleaguesAsync()).thenReturn(Single.just(colleagues))
        val testObserver = peopleRepository.getColleagues().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun colleagues_repositoryComplete_with_errors() {
        val error = ColleaguesFactory.makeResponseError()
        Mockito.`when`(mockDataSource.getColleaguesAsync()).thenReturn(Single.error(error))
        val testObserver = peopleRepository.getColleagues().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun colleagues_repository_returns_data() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        Mockito.`when`(mockDataSource.getColleaguesAsync()).thenReturn(Single.just(colleagues))
        val testObserver = peopleRepository.getColleagues().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(colleagues)
    }


}