package com.spider.b2b.di.component

import com.spider.b2b.B2BApplication
import com.spider.b2b.di.module.ActivityBuilder
import com.spider.b2b.di.module.ApplicationModule
import com.spider.b2b.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (NetworkModule::class),
        (ApplicationModule::class),
        (ActivityBuilder::class)]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: B2BApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: B2BApplication)
}
