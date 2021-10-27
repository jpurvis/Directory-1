package com.example.colleagues.interactors

import com.example.colleagues.domain.PeopleResponseItem
import com.example.colleagues.domain.repository.IPeopleRepository
import com.example.common.executor.PostExecutionThread
import com.example.common.executor.ThreadExecutor
import com.example.common.utils.OpenForTesting
import com.example.common.utils.SingleUseCase
import io.reactivex.Single

@OpenForTesting
open class GetColleagues(
    private val peopleRepository: IPeopleRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<PeopleResponseItem>, Int, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(
        params: Int?,
        arg2: String?
    ): Single<List<PeopleResponseItem>> {
        return peopleRepository.getColleagues()
    }
}