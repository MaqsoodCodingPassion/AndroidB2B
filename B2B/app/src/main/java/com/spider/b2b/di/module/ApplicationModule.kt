package com.spider.b2b.di.module

import com.spider.b2b.di.BaseUrl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "http://192.168.100.61:2020"
    }
}