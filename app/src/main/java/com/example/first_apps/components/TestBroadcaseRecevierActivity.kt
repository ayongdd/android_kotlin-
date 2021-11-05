package com.example.first_apps.components

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class TestBroadcaseRecevierActivity:AppCompatActivity() {
    private lateinit var receiver:TestBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = TestBroadcastReceiver()
        //创建广播过滤器，指定只接收android.net.conn.CONNECTIVITY_ACTION的广播事件
        //静态发送广播
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(receiver,intentFilter)

        //应用内发送广播 安全性高&效率高
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.first_apps.TEST_BROADCAST_RECEVIER")
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,intentFilter)
        Handler().postDelayed({ //延迟3秒
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("com.example.first_apps.TEST_BROADCAST_RECEVIER"))
        },2000)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(receiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}