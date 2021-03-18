package com.harish.tfl.repository

import com.harish.tfl.data.RoadStatusModel
import io.reactivex.Single

interface RemoteDataSource {
    fun getRoadStatus(roadName: String): Single<List<RoadStatusModel>>
}