package com.spider.b2b.service

import com.spider.b2b.model.InvoiceListResponse
import com.spider.b2b.model.ProductListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("v1/adminservice/products")
    fun getProductsList(): Single<List<ProductListResponse>>

    @GET("v1/invoice/invoiceInfo")
    fun getInvoiceList(): Single<List<InvoiceListResponse>>

}
