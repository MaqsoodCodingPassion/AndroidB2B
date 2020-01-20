package com.spider.b2b.productList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.spider.b2b.B2BViewModel
import com.spider.b2b.R
import com.spider.b2b.WebViewDemo
import com.spider.b2b.activity.WebviewActivity
import com.spider.b2b.model.ProductListResponse
import com.spider.b2b.util.OnItemClickListener
import com.spider.b2b.util.ViewModelFactory
import com.spider.b2b.util.addOnItemClickListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_product_list.*
import javax.inject.Inject

class ProductListActivity : AppCompatActivity() {

    private lateinit var viewModel: B2BViewModel
    private var productListAdapter: ProductListAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var productsList: MutableList<List<ProductListResponse>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(B2BViewModel::class.java)
        initAdapter()
        callFetchGamesListAPI()
        observeLiveData()
    }

    private fun initAdapter() {
        productsList = ArrayList()
        productListAdapter = ProductListAdapter(
            this,
            productsList as ArrayList<ProductListResponse>
        )
        productListRecyclerView.itemAnimator = DefaultItemAnimator()
        productListRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        productListRecyclerView.adapter = productListAdapter

        productListRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                launchWebView()
            }
        })
    }

    private fun launchWebView() {
        startActivity(Intent(this, WebViewDemo::class.java))
    }

    private fun callFetchGamesListAPI() {
        viewModel.fetchProductListFromService()
    }

    private fun observeLiveData() {
        viewModel.productListResponse.observe(this, Observer {
            if (it != null) {
                productsList?.clear()
                productsList?.add(it!!)
                productsList?.let { it1 -> productListAdapter?.setDataList(it) }
                productListAdapter?.notifyDataSetChanged()
            }
        })
    }
}
