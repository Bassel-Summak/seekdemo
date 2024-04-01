package com.basapps.seekdemo.di

import com.basapps.seekdemo.job.data.JobRepository
import com.basapps.seekdemo.job.data.JobRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object JobModule {


    @Provides
    fun provideJobRepository(impl: JobRepositoryImpl): JobRepository = impl

}
