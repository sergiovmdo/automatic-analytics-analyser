package com.example.automatic_analytics_analyser.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.automatic_analytics_analyser.di.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(FeedbackViewModel::class)
//    abstract fun feedbackViewModel(viewModel: FeedbackViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}