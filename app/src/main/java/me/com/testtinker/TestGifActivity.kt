package me.com.testtinker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_test_gif.*
import me.com.t.GifUtil


class TestGifActivity : AppCompatActivity(),View.OnClickListener,GifUtil.GifListener {
    override fun gifPlayComplete(imageView: ImageView?) {
        when(imageView?.id){
            R.id.pickupImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_select).into(pickupImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_shipping_unselect).into(shippingImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_station_unselect).into(stationImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_me_unselect).into(meImageView)
            }
            R.id.shippingImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_shipping_select).into(shippingImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_unselect).into(pickupImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_station_unselect).into(stationImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_me_unselect).into(meImageView)
            }
            R.id.stationImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_station_select).into(stationImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_unselect).into(pickupImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_shipping_unselect).into(shippingImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_me_unselect).into(meImageView)
            }
            R.id.meImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_me_select).into(meImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_unselect).into(pickupImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_shipping_unselect).into(shippingImageView)
                Glide.with(this@TestGifActivity).load(R.drawable.ic_station_unselect).into(stationImageView)
            }
        }
    }

    var imageId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_gif)
        initView()
    }

    private fun initView() {
        Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_select).into(pickupImageView)
        pickupLinearlayout.setOnClickListener(this)
        shippingLinearlayout.setOnClickListener(this)
        stationLinearlayout.setOnClickListener(this)
        meLinearlayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.pickupLinearlayout->{
                loadGif(pickupImageView,R.drawable.ic_pickup_active)
            }
            R.id.shippingLinearlayout->{
                loadGif(shippingImageView,R.drawable.ic_shipping_active)
            }
            R.id.stationLinearlayout->{
                loadGif(stationImageView,R.drawable.ic_station_active)
            }
            R.id.meLinearlayout->{
                loadGif(meImageView,R.drawable.ic_me_active)
            }
        }
    }

    private fun loadGif(imageView: ImageView?,obj:Any){
        if(imageId!=imageView?.id){
            getImageId()
            imageId = imageView?.id!!
            GifUtil.loadOneTimeGif(this@TestGifActivity,obj,imageView,this)
        }
    }

    private fun getImageId(){
        when(imageId){
            0->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_unselect).into(pickupImageView)
            }
            R.id.pickupImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_pickup_unselect).into(pickupImageView)
            }
            R.id.shippingImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_shipping_unselect).into(shippingImageView)
            }
            R.id.stationImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_station_unselect).into(stationImageView)
            }
            R.id.meImageView->{
                Glide.with(this@TestGifActivity).load(R.drawable.ic_me_unselect).into(meImageView)
            }
        }
    }
}
