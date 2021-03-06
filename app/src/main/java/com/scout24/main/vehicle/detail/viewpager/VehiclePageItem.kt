package com.scout24.main.vehicle.detail.viewpager

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.scout24.R
import com.scout24.datasets.Images
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.vehicle_page_item.view.*
import java.lang.Exception

/**
 * Created by Sid on 16/06/2018.
 *
 * Custom View to hold vehicle images in the form of pages
 */

class VehiclePageItem : ConstraintLayout, VehiclePageItemMvp.View {

    private val presenter = VehiclePageItemPresenter(this)

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        val view: View = View.inflate(context, R.layout.vehicle_page_item, null)
        val params = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        addView(view)
    }

    fun bind(images: Images) {
        presenter.bind(images)
    }

    override fun loadImage(imageUrl: String) {
        Picasso.get()
                .load(imageUrl)
                .into(vehicleImage, object : Callback {
                    override fun onSuccess() {
                        presenter.onImageLoadingSuccess()
                    }

                    override fun onError(e: Exception?) {
                        presenter.onImageLoadingFailed()
                    }

                })
    }

    override fun loadImage(resourceId: Int) {
        Picasso.get()
                .load(resourceId)
                .into(vehicleImage)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}