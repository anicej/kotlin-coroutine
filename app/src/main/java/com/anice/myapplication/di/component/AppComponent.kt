package com.anice.myapplication.di.component

import android.app.Application
import com.anice.myapplication.application.MyApplication
import com.anice.myapplication.di.module.ActivityBuilderModule
import com.anice.myapplication.di.module.NetworkModule
import com.anice.myapplication.di.module.ServiceModule
import com.anice.myapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        ServiceModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
}