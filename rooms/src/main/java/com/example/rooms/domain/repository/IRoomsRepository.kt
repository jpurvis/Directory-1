package com.example.rooms.domain.repository

import com.example.rooms.domain.RoomsResponseItem
import io.reactivex.Single

interface IRoomsRepository {
    fun getRooms(): Single<List<RoomsResponseItem>>
}