package com.example.rooms.interactors

import com.example.common.executor.PostExecutionThread
import com.example.common.executor.ThreadExecutor
import com.example.common.utils.OpenForTesting
import com.example.common.utils.SingleUseCase
import com.example.rooms.domain.RoomsResponseItem
import com.example.rooms.domain.repository.IRoomsRepository
import io.reactivex.Single

@OpenForTesting
open class GetRooms(
    private val roomsRepository: IRoomsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<RoomsResponseItem>, Int, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(
        params: Int?,
        arg2: String?
    ): Single<List<RoomsResponseItem>> {
        return roomsRepository.getRooms()
    }
}