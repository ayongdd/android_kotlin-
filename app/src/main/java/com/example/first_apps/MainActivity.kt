package com.example.first_apps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.first_apps.http.ApiService
import com.example.first_apps.http.HiOkHttp
import com.example.first_apps.http.HiRetrofit
import com.example.first_apps.http.UserResponse
import com.google.android.material.button.MaterialButtonToggleGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//工程入口文件
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) //关联文件名
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//        navView.setupWithNavController(navController)

//        Activity之间传参
        val textView = TextView(this)
        textView.text="MainActivity"
        textView.gravity = Gravity.CENTER
        setContentView(textView)
        textView.setOnClickListener {
////           显示启动
//            val intent = Intent(MainActivity@this,SecondActivity::class.java)
//            隐示启动
            val intent = Intent();
            intent.action = "com.example.first_apps.action.SECONDACTIVITY"
            intent.addCategory("com.example.first_apps.action.SECONDACTIVITY")

            intent.putExtra("extra_data","extra_data")
            intent.putExtra("extra_int_data",100)
            startActivity(intent)
//            startActivityForResult(intent,1000)

            //跳转到打电话页面
//            val uri = Uri.parse("tel:10086")
//            val intent = Intent(Intent.ACTION_DIAL,uri)
//            startActivity(intent)
        }

//        HiOkHttp.get()
//        HiOkHttp.getAsync()
//        HiOkHttp.post()
//        HiOkHttp.postAsync()

        //onFailure 和onResponse 的回调是在主线程的
//        val apiService = HiRetrofit.create(ApiService::class.java)
//        apiService.queryUser("1344412").enqueue(object : Callback<UserResponse> {
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Log.e("Retrofit",t.message?:"error")
//            }
//
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                Log.e("Retrofit111",response.body()?.toString()?:"response is null")
//            }
//        })
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}