package com.example.automatic_analytics_analyser.di.injector

import com.example.automatic_analytics_analyser.AAAApp
import com.example.automatic_analytics_analyser.di.component.AppComponent
import com.example.automatic_analytics_analyser.di.component.DaggerAppComponent

/**
 * Injects the dependencies into the given [app] instance
 */
fun initInjector(app: AAAApp) {
    val appComponent: AppComponent = DaggerAppComponent.builder().application(app).build()
    appComponent.inject(app)
}