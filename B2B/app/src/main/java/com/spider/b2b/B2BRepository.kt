package com.spider.b2b

import com.spider.b2b.model.InvoiceListResponse
import com.spider.b2b.model.ProductListResponse
import com.spider.b2b.service.Service
import io.reactivex.Single
import javax.inject.Inject

class B2BRepository @Inject constructor(private val service: Service) {

    fun fetchProductListFromService(): Single<List<ProductListResponse>> {
        return service.getProductsList()
    }

    fun fetchInvoiceListFromService(): Single<List<InvoiceListResponse>> {
        return service.getInvoiceList()
    }
}
