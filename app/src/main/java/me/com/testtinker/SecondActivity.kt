package me.com.testtinker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
    when (v?.id){
        R.id.imageView ->{
            Log.e("load gif","gif");
//            Glide.with(this).load("https://upload-images.jianshu.io/upload_images/5885602-edb0d53965efd964.gif").into(imageView);
            Glide.with(this).load(R.drawable.ic_gif).into(imageView);
        }
    }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initView()
    }

    fun initView(){
        imageView.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this).clearMemory();
    }
}
