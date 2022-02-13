package com.zywczas.myworkout.di.modules

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

//    @ViewModelScoped todo sprawdzic czy to ma sens - czy jakies reposytorium bedzie budowane dopiero przy view model i czy bedzie dalej to samo po obrocie

}