package com.spider.b2b.di.module

import com.spider.b2b.invoiceList.InvoiceListActivity
import com.spider.b2b.productList.ProductListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindActivityMain(): ProductListActivity

    @ContributesAndroidInjector()
    abstract fun bindInvoiceListActivity(): InvoiceListActivity
}