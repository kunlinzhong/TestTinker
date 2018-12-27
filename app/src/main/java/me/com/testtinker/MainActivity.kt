package me.com.testtinker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.tencent.tinker.lib.util.TinkerLog
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback
import kotlinx.android.synthetic.main.activity_main.*
import me.com.baselibrary.LogUtils
import me.com.t.StrUtil
import me.com.t.StrUtil.msgFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = "MainActivity-->3.0.8patch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Log.e(TAG, "update patch success");
        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        initView()
//        Toast.makeText(this, "update patch success", Toast.LENGTH_SHORT).show()
    }


    fun initView() {
        btn1.setOnClickListener(this)
        requestConfig.setOnClickListener(this)
        killSelf.setOnClickListener(this)
        nextPage.setOnClickListener(this)
        getStr.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                TinkerPatch.with().fetchPatchUpdate(true)
//                Log.e(TAG, "click btn1");
                LogUtils.e(TAG, "click btn1")
                Toast.makeText(this, "click btn1---->", Toast.LENGTH_SHORT).show()
//                val intent = Intent()
//                intent.setClass(this, SecondActivity::class.java)
//                startActivity(intent)
            }
            R.id.requestConfig -> {
//                Log.e(TAG, "request config");
                LogUtils.e(TAG, "request config")
                Toast.makeText(this, "onclick requestConfig--->", Toast.LENGTH_SHORT).show()
                TinkerPatch.with().fetchDynamicConfig(object : ConfigRequestCallback {
                    override fun onSuccess(configs: HashMap<String, String>) {
                        TinkerLog.w(TAG, "request config success, config:" + configs)
                    }

                    override fun onFail(e: Exception) {
                        TinkerLog.w(TAG, "request config failed, exception:" + e)
                    }
                }, true)
            }
            R.id.killSelf -> {
                LogUtils.e(TAG, "kill self")
                Toast.makeText(this, "onclick killSelf--->", Toast.LENGTH_SHORT).show()
                ShareTinkerInternals.killAllOtherProcess(applicationContext)
                android.os.Process.killProcess(android.os.Process.myPid())
            }
            R.id.nextPage -> {
                LogUtils.e(TAG, "next page")
                Toast.makeText(this, "onclick nextPage--->", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.setClass(this, SecondActivity::class.java)
                startActivity(intent)
            }
            R.id.getStr->{
                val ss = StrUtil.msgFormat()
                getStr.setText(ss)
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
