package com.example.first_apps.components

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log

class TextService2:Service() {
    private val TAG = "TextService2"
    private var count = 0
    private var quit = false
    override fun onCreate() {
        Log.e(TAG,"onCreate")
        super.onCreate()

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
          val notification=  Notification.Builder(applicationContext,"channel_id").build()
            startForeground(1,notification)
        }


        Thread(Runnable {
            while (true) {
                if(quit) {
                    break
                }
                Thread.sleep(1000)
                count++
            }
        }).start()
    }

    private  val binder = MyBinder()

    inner class MyBinder: Binder() {
        fun getCount():Int {
            return count
        }
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG,"onBind")
        return binder
    }

    /*
       * 通过bindService启动的服务，onStartCommand是不会被触发的
       * */

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("Service","onStartCommand")

        //在这里do 文件上传下载 异步任务等等。
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("Service","onUnbind")
        quit = true
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        //只能调用stopService才能触发
        Log.e("Service","onDestroy")
        super.onDestroy()
    }

}