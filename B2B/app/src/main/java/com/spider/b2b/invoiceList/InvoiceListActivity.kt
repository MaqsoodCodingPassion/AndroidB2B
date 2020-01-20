package com.spider.b2b.invoiceList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.spider.b2b.R
import com.spider.b2b.model.InvoiceListResponse
import com.spider.b2b.B2BViewModel
import com.spider.b2b.util.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_invoice_list.*
import kotlinx.android.synthetic.main.activity_product_list.*
import javax.inject.Inject

class InvoiceListActivity : AppCompatActivity(){

    private lateinit var viewModel: B2BViewModel
    private var invoiceListAdapter: InvoiceListAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var productsList: MutableList<List<InvoiceListResponse>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_list)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(B2BViewModel::class.java)
        initAdapter()
        callFetchGamesListAPI()
        observeLiveData()
    }

    private fun initAdapter() {
        productsList = ArrayList()
        invoiceListAdapter = InvoiceListAdapter(this,
            productsList as ArrayList<InvoiceListResponse>
        )
        invoiceRecyclerView.itemAnimator = DefaultItemAnimator()
        invoiceRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        invoiceRecyclerView.adapter = invoiceListAdapter
    }

    private fun callFetchGamesListAPI() {
        viewModel.fetchInvoiceListFromService()
    }

    private fun observeLiveData() {
        viewModel.invoiceListResponse.observe(this, Observer {
            if (it != null) {
                productsList?.clear()
                productsList?.add(it!!)
                productsList?.let { it1 -> invoiceListAdapter?.setDataList(it) }
                invoiceListAdapter?.notifyDataSetChanged()
            }
        })
    }
}