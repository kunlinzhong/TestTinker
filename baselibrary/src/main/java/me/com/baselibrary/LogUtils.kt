package me.com.baselibrary

import android.util.Log
object LogUtils{
    val isDebug = true;
    /**
     * e类型的log.e日志
     */
    fun e(tag: String,msg:String){
        if(isDebug){
            Log.e(tag,msg)
        }

    }
    /**
     * v类型的log.v日志
     */
    fun v(tag: String, msg: String){
        if(isDebug){
            Log.v(tag,msg)
        }
    }
}