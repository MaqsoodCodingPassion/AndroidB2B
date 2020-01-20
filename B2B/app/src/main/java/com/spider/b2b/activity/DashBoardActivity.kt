package com.spider.b2b.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.spider.b2b.OptionsInteractionListener
import com.spider.b2b.R
import com.spider.b2b.adapter.OptionListAdapter
import com.spider.b2b.custom.SpacesItemDecoration
import com.spider.b2b.invoiceList.InvoiceListActivity
import com.spider.b2b.model.Option
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {

    private val dashboardOptions = ArrayList<Option>()

    private val optionsInteractionListener = object :
        OptionsInteractionListener {
        override fun onClick(id: Int) {
            when (id) {
                R.string.id_add_amount -> onAddAmountClicked()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun onAddAmountClicked() {
       startActivity(
            Intent(
                this, InvoiceListActivity::class.java
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        // showSuccessDialog("Added Successfully",this)
        prepareContent()
        setGridView()
    }

    private fun prepareContent() {
        dashboardOptions.add(
            Option(
                getString(R.string.title_cloths),
                R.string.id_add_amount,
                R.drawable.ic_shopping
            )
        )
        dashboardOptions.add(
            Option(
                getString(R.string.title_phone),
                R.string.id_phone,
                R.drawable.ic_phone
            )
        )
        dashboardOptions.add(
            Option(
                getString(R.string.title_grocery),
                R.string.id_grocery,
                R.drawable.ic_grocery
            )
        )
        dashboardOptions.add(
            Option(
                getString(R.string.title_electronics),
                R.string.title_electronics,
                R.drawable.ic_desktop
            )
        )
        dashboardOptions.add(
            Option(
                getString(R.string.title_rewards),
                R.string.id_rewards,
                R.drawable.rewards
            )
        )
        dashboardOptions.add(
            Option(
                getString(R.string.title_promotion),
                R.string.id_promotion,
                R.drawable.promotion
            )
        )
    }

    private fun setGridView() {
        rvDashboard.invalidate()
        rvDashboard.layoutManager = GridLayoutManager(this, 2)
        rvDashboard.addItemDecoration(SpacesItemDecoration(10))
        rvDashboard.adapter = OptionListAdapter(optionsInteractionListener, dashboardOptions)
    }
}
