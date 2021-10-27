package com.example.directory.framework

import com.example.colleagues.domain.PeopleResponseItem
import com.example.directory.BuildConfig
import com.example.rooms.domain.RoomsResponseItem
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(BuildConfig.PATH + BuildConfig.PEOPLE)
    fun getColleagues(): Single<List<PeopleResponseItem>>

    @GET(BuildConfig.PATH + BuildConfig.ROOMS)
    fun getRooms(): Single<List<RoomsResponseItem>>
}