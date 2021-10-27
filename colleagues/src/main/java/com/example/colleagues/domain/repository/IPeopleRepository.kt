package com.example.colleagues.domain.repository

import com.example.colleagues.domain.PeopleResponseItem
import io.reactivex.Single

interface IPeopleRepository {
    fun getColleagues(): Single<List<PeopleResponseItem>>
}