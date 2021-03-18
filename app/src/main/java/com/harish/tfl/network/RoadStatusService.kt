package com.harish.tfl.network

import com.harish.tfl.data.RoadStatusModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoadStatusService {

    @GET("road/{road}")
    fun getRoadStatus(
        @Path("road") roadName: String,
        @Query("app_key") appKey: String
    ): Single<List<RoadStatusModel>>
}