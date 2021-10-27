package com.example.colleagues.data.repository

import com.example.colleagues.data.datasource.IPeopleDataSource
import com.example.colleagues.domain.PeopleResponseItem
import com.example.colleagues.domain.repository.IPeopleRepository
import com.example.common.utils.OpenForTesting
import io.reactivex.Single

@OpenForTesting
class PeopleRepository(private val dataSource: IPeopleDataSource) : IPeopleRepository {
    override fun getColleagues(): Single<List<PeopleResponseItem>> {
        return dataSource.getColleaguesAsync()
    }
}