package me.com.testtinker

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import kotlinx.android.synthetic.main.activity_second.*
import me.com.t.SPLASH_WHAT
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.com.t.StrUtil
import java.lang.reflect.InvocationTargetException
import me.com.baselibrary.AutoTouch

class SecondActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
    when (v?.id){
        R.id.imageView ->{
//            Glide.with(this).load("https://upload-images.jianshu.io/upload_images/5885602-edb0d53965efd964.gif").into(imageView);
//            loadOneTimeGif(this,R.drawable.ic_gif_2,imageView,gifListener);
            Glide.with(this).load(R.drawable.ic_gif_2).into(imageView);
            startSplashThread()
        }
    }
    }

    val gifListener = object : GifListener {
        override fun gifPlayComplete() {
            Glide.with(this@SecondActivity).load(R.drawable.ic_gif).into(imageView);
        }
    }
    fun startSplashThread(){
        val splashThread = object : Thread() {
            //创建子线程
            override fun run() {
                try {
                    Thread.sleep(1000)//使程序休眠1秒
                    var message = Message()
                    message.what = SPLASH_WHAT
                    handler.sendMessage(message)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        splashThread.start()
    }
    // 创建一个Handler
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                SPLASH_WHAT -> {
                    Glide.with(this@SecondActivity).load(R.drawable.ic_gif).into(imageView);
                } else -> { // 这里的else相当于Java中switch的default;
                    val mBundle = msg?.data
                }
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
        Glide.with(this@SecondActivity).load(R.drawable.ic_gif).into(imageView);
//        val autoTouch = AutoTouch()
//        autoTouch.autoClickPos(0.0, 0.0, 500.0, 500.0)
//        StrUtil.testInt(1,"--->")
    }

    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this).clearMemory();
    }

    fun loadOneTimeGif(context: Context, model: Any, imageView: ImageView, gifListener: GifListener?) {
        Glide.with(context).asGif().load(model).listener(object : RequestListener<GifDrawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<GifDrawable>,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: GifDrawable,
                model: Any,
                target: Target<GifDrawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                try {
                    val gifStateField = GifDrawable::class.java.getDeclaredField("state")
                    gifStateField.isAccessible = true
                    val gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable\$GifState")
                    val gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader")
                    gifFrameLoaderField.isAccessible = true
                    val gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader")
                    val gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder")
                    gifDecoderField.isAccessible = true
                    val gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder")
                    val gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)))
                    val getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", Int::class.javaPrimitiveType)
                    getDelayMethod.isAccessible = true
                    //设置只播放一次
                    resource.setLoopCount(1)
                    //获得总帧数
                    val count = resource.frameCount
                    var delay = 0
                    for (i in 0 until count) {
                        //计算每一帧所需要的时间进行累加
                        delay += getDelayMethod.invoke(gifDecoder, i) as Int
                    }
                    imageView.postDelayed({
                        gifListener?.gifPlayComplete()
                    }, delay.toLong())
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }

                return false
            }
        }).into(imageView)
    }

    /**
     * Gif播放完毕回调
     */
    interface GifListener {
        fun gifPlayComplete()
    }
}
