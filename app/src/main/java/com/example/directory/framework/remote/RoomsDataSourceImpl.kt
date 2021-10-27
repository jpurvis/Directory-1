package com.example.directory.framework.remote

import com.example.directory.framework.ApiService
import com.example.rooms.data.datasource.IRoomsDataSource
import com.example.rooms.domain.RoomsResponseItem
import io.reactivex.Single

class RoomsDataSourceImpl(private val apiService: ApiService) : IRoomsDataSource {
    override fun getRoomsAsync(): Single<List<RoomsResponseItem>> {
        return apiService.getRooms()
    }
}