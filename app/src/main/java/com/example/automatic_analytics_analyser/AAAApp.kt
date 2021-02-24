package com.example.automatic_analytics_analyser

import android.app.Application
import com.example.automatic_analytics_analyser.di.injector.initInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AAAApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        initInjector(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}