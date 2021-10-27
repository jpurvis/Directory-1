package com.example.directory.framework.remote

import com.example.colleagues.data.datasource.IPeopleDataSource
import com.example.colleagues.domain.PeopleResponseItem
import com.example.directory.framework.ApiService
import io.reactivex.Single

class PeopleDataSourceImpl(private val apiService: ApiService) : IPeopleDataSource {
    override fun getColleaguesAsync(): Single<List<PeopleResponseItem>> {
        return apiService.getColleagues()
    }
}