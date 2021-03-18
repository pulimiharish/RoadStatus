package com.harish.tfl.repository

import com.harish.tfl.data.RoadStatusModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoadStatusRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RoadStatusRepository {

    override fun getRoadStatus(roadName: String): Single<List<RoadStatusModel>> {
        return remoteDataSource.getRoadStatus(roadName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}