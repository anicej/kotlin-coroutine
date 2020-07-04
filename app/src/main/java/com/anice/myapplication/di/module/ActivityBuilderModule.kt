package com.anice.myapplication.di.module

import com.anice.myapplication.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentBuilderModule::class])
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    fun contributeTodoActivity(): MainActivity
}