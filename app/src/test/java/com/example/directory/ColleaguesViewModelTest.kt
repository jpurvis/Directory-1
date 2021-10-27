package com.example.directory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.example.colleagues.domain.PeopleResponseItem
import com.example.colleagues.interactors.GetColleagues
import com.example.common.utils.Resource
import com.example.directory.presentation.colleagues.ColleaguesViewModel
import com.example.directory.utils.ColleaguesFactory
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
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class ColleaguesViewModelTest {
    private lateinit var mockGetColleaguesUseCase: GetColleagues
    private lateinit var colleaguesViewModel: ColleaguesViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockGetColleaguesUseCase = mock()
        colleaguesViewModel = ColleaguesViewModel(mockGetColleaguesUseCase)
    }

    @Test
    fun test_loading_is_emitted() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        colleaguesViewModel.getColleagues()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(colleaguesViewModel.colleagues) { result ->
            Assert.assertTrue(result is Resource.Loading)
        }
    }

    @Test
    fun test_error_is_emitted() {
        stubWheneverThenReturn(Single.error(RuntimeException()))
        colleaguesViewModel.getColleagues()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(colleaguesViewModel.colleagues) { result ->
            Assert.assertTrue(result is Resource.Error)
        }
    }

    @Test
    fun test_data_is_emitted() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        colleaguesViewModel.getColleagues()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(colleaguesViewModel.colleagues) { result ->
            Assert.assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun test_data_emitted_is_listOf_colleagues() {
        val colleagues = ColleaguesFactory.makeColleaguesResponse()
        stubWheneverThenReturn(Single.just(colleagues))
        colleaguesViewModel.getColleagues()
        val mediatorLiveData = MediatorLiveData<Resource<List<PeopleResponseItem>>>()
        mediatorLiveData.addSource(colleaguesViewModel.colleagues) { result ->
            when (result) {
                is Resource.Success -> {
                    val result = result.result
                    Assert.assertEquals(colleagues, result)
                }
            }
        }
    }

    private fun stubWheneverThenReturn(single: Single<List<PeopleResponseItem>>) {
        whenever(mockGetColleaguesUseCase.buildUseCaseObservable(any(), any())).thenReturn(single)
    }
}