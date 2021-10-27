package com.example.directory.framework

import com.example.colleagues.domain.PeopleResponseItem
import com.example.directory.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(BuildConfig.PATH + BuildConfig.PEOPLE)
    fun getColleagues(): Single<List<PeopleResponseItem>>
}