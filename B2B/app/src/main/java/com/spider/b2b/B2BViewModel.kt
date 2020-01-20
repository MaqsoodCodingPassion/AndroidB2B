package com.spider.b2b

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spider.b2b.model.InvoiceListResponse
import com.spider.b2b.model.ProductListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class B2BViewModel(val repository: B2BRepository) : ViewModel() {

    var productListResponse: MutableLiveData<List<ProductListResponse>?> = MutableLiveData()

    var invoiceListResponse: MutableLiveData<List<InvoiceListResponse>?> = MutableLiveData()

    fun fetchProductListFromService(): MutableLiveData<List<ProductListResponse>?> {

        val observable = repository.fetchProductListFromService()

        observable.map<List<ProductListResponse>> {
            it
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    productListResponse.value = it
                },
                {
                    productListResponse.value = null
                })

        return productListResponse
    }

    fun fetchInvoiceListFromService(): MutableLiveData<List<InvoiceListResponse>?> {

        val observable = repository.fetchInvoiceListFromService()

        observable.map<List<InvoiceListResponse>> {
            it
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    invoiceListResponse.value = it
                },
                {
                    invoiceListResponse.value = null
                })

        return invoiceListResponse
    }
}