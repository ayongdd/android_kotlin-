package com.example.first_apps.components

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.first_apps.R
import kotlinx.android.synthetic.main.activity_text_service.*

class TextServiceActivity : AppCompatActivity() {
    private var myBinder:TextService2.MyBinder?=null;
    private var connection:ServiceConnection?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_service)
        connection = object :ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
                Log.e("TextService2","------Service Disconnected------")
            }
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.e("TextService2","------Service Connected------")
                myBinder = service as TextService2.MyBinder
            }
        }
        /*
        * bindService是运行一些和 Activity生命周期相等的后台任务，比如跨进程通讯
        * */
//        val intent = Intent(this,TextService2::class.java)
//        bindService(intent,connection!!, Context.BIND_AUTO_CREATE)

        Handler().postDelayed(Runnable {
//            startService(Intent(this@TextServiceActivity,TextService2::class.java))
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) { //判断android版本是否高与8.0
                startForegroundService(Intent(this,TextService2::class.java))
            }else {
                startService(Intent(this,TextService2::class.java))
            }
        },70*1000) //系统60秒之后才会判定app处于后台
        start_service.setOnClickListener {
//            val intent = Intent(this,TextService1::class.java)
//            startService(intent)

            Log.e("TextService2","getCount=${myBinder?.getCount()}")
        }

        stop_service.setOnClickListener {
//            val intent = Intent(this,TextService1::class.java)
//            stopService(intent)
            unbindService(connection!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection!!) //防止内存泄漏
    }
}