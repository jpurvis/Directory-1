package com.example.rooms.data.repository

import com.example.common.utils.OpenForTesting
import com.example.rooms.data.datasource.IRoomsDataSource
import com.example.rooms.domain.RoomsResponseItem
import com.example.rooms.domain.repository.IRoomsRepository
import io.reactivex.Single

@OpenForTesting
class RoomsRepository(private val dataSource: IRoomsDataSource) : IRoomsRepository {
    override fun getRooms(): Single<List<RoomsResponseItem>> {
        return dataSource.getRoomsAsync()
    }
}