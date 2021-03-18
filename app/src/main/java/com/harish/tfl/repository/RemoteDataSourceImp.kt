package com.harish.tfl.repository

import com.harish.tfl.BuildConfig
import com.harish.tfl.data.RoadStatusModel
import com.harish.tfl.network.RoadStatusService
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val roadStatusService: RoadStatusService) :
    RemoteDataSource {

    override fun getRoadStatus(roadName: String): Single<List<RoadStatusModel>> {
        return roadStatusService.getRoadStatus(roadName, BuildConfig.TFL_KEY)
    }
}