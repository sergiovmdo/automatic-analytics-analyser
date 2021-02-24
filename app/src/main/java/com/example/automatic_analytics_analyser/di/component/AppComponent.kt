package com.example.automatic_analytics_analyser.di.component

import android.app.Application
import com.example.automatic_analytics_analyser.AAAApp
import com.example.automatic_analytics_analyser.di.module.ActivitiesModule
import com.example.automatic_analytics_analyser.di.module.AppModule
import com.example.automatic_analytics_analyser.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Main component of the application and the one injected into the application scope.
 *
 * It contains all the modules the app needs.
 */

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ViewModelModule::class,
        ActivitiesModule::class
    ]
)
interface AppComponent : AndroidInjector<AAAApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}