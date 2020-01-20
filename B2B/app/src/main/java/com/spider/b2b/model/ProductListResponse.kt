package com.spider.b2b.model

import com.google.gson.annotations.SerializedName

class ProductListResponse {
    @SerializedName("image")
    var image: Any? = null
    @SerializedName("productId")
    var productId = 0
    @SerializedName("price")
    var price = 0
    @SerializedName("description")
    var description: String? = null
    @SerializedName("categories")
    var categories: Categories? = null
    @SerializedName("productName")
    var productName: String? = null

}