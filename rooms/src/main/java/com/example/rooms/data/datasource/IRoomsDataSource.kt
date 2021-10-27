package com.example.rooms.data.datasource

import com.example.rooms.domain.RoomsResponseItem
import io.reactivex.Single

interface IRoomsDataSource {
    fun getRoomsAsync(): Single<List<RoomsResponseItem>>
}