package com.anice.myapplication.di.module

import com.anice.myapplication.service.AppService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideTodoService(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }
}