package com.example.first_apps.components

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TextService1:Service() {
    override fun onCreate() {
        super.onCreate()
        Log.e("Service","create")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("Service","onBind")
        return null
    }

    /*
    * 对于使用startService的方式而言，onStartCommand就是我们用于做后台任务的地方
    * 如果我们多次调用startService，会直接回调onStartCommand，而不再回调onCreate
    * 这种方式启动的服务，它的生命周期跟应用程勋的生命周期一样长，只要应用程序不被杀死，服务就会一直运行着，除非我们调用stopService
    * startService 一般来说是用于创建一个长时间持续运行的后台任务的时候才会使用，比如说socket,文件上传下载服务
    * */

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("Service","onStartCommand")

        //在这里do 文件上传下载 异步任务等等。
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("Service","onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        //只能调用stopService才能触发
        Log.e("Service","onDestroy")
        super.onDestroy()
    }

}