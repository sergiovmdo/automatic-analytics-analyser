package com.example.automatic_analytics_analyser.di.module

import android.app.Application
import com.example.automatic_analytics_analyser.AAAApp
import com.example.automatic_analytics_analyser.data.api.ApiService
import com.example.automatic_analytics_analyser.data.api.createApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesApplication(app: Application): AAAApp {
        return app as AAAApp
    }

    @Singleton
    @Provides
    fun providesApiService(): ApiService {
        return createApi(ApiService::class.java)
    }

}