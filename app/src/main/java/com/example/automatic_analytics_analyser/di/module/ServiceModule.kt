package com.example.automatic_analytics_analyser.di.module

import com.example.automatic_analytics_analyser.notifications.AAANotificationsService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract fun contributeFCMService() : AAANotificationsService
}