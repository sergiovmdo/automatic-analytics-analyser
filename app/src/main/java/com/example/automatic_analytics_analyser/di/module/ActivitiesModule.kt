package com.example.automatic_analytics_analyser.di.module

import com.example.automatic_analytics_analyser.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}