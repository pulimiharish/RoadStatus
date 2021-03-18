package com.harish.tfl.di

import com.harish.tfl.repository.RoadStatusRepository
import com.harish.tfl.repository.RoadStatusRepositoryImp
import com.harish.tfl.repository.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RoadStatusActivityModule {

    @Provides
    @ViewModelScoped
    fun provideRoadStatusRepository(
        remoteDataSource: RemoteDataSource
    ): RoadStatusRepository = RoadStatusRepositoryImp(remoteDataSource)

}