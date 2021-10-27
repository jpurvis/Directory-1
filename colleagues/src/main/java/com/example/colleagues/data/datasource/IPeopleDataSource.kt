package com.example.colleagues.data.datasource

import com.example.colleagues.domain.PeopleResponseItem
import io.reactivex.Single

interface IPeopleDataSource {
    fun getColleaguesAsync(): Single<List<PeopleResponseItem>>
}