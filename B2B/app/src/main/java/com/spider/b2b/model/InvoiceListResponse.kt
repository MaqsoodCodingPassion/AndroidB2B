package com.spider.b2b.model

import com.google.gson.annotations.SerializedName

class InvoiceListResponse {
    @SerializedName("billAmount")
    var billAmount = 0
    @SerializedName("invoiceId")
    var invoiceId = 0
    @SerializedName("gstin")
    var gstin: String? = null

}