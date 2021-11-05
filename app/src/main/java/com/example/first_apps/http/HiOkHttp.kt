package com.example.first_apps.http

import android.os.Environment
import android.util.Log
import android.widget.Toast
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.File

object  HiOkHttp {
//    private val BASE_URL= "https://123.56.232.18:8080/serverdemo";
    private val BASE_URL= "https://cart.lazada.com.ph/cart/api/count";
    private val URL = "https://www.imooc.com/search/hotwords";
    private val client:OkHttpClient


    //初始化 里面的代码最先执行

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor() //okhttp 自带打印中间件
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        client =  OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) //链接超时时间 10秒
            .readTimeout(10,TimeUnit.SECONDS)  //读取超时时间 10秒
            .writeTimeout(10,TimeUnit.SECONDS) //写超时 10秒
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(LoggingInterceptor()) //插入拦截器
            .build()
    }
    //android 分为主线程，和子线程
    //主线程就是App一启动后，咱们android framework层会启动一个线程，主线程（UI线程）
    //子线程 --new Thread().start()
    //无论同步还是异步 onFailure,onResponse 的回调是在子线程中的，我们需要切换到主线程才能操作UI控件

    //同步请求get方法
    fun get () {
        //构造请求体
        val request:Request  = Request.Builder()
            .url("$BASE_URL")
            .build()
        //构造请求对象
        val call = client.newCall(request)

        //发起同步请求execute --同步执行，100ms,1000ms
        val response = call.execute()
        val body = response.body?.string()
        print("get response:${body}")
    }
    //异步请求getAsync
    fun getAsync () {
        //构造请求体
        val request:Request  = Request.Builder()
            .url("$BASE_URL")
            .build()
        //构造请求对象
        val call = client.newCall(request)
        //异步请求 enqueue
        call.enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP","get response onFailure:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.e("OKHTTP","get response:${body}")
            }
        })
    }

    //post 同步请求 表单提交
    fun post() {
        println("开始")
        val body = FormBody.Builder()
            .add("key1","value1")
            .add("key2","value2")
            .build()

        val request = Request.Builder().url(URL)
            .post(body)
            .build()

        val call = client.newCall(request)
        Thread(Runnable { //创建线程
            println("进入")
            val response = call.execute()
            Log.e("OKHTTP","post response:${response.body?.string()}")
        })

    }

    //post 异步请求 表单提交
    fun postAsync() {
        println("开始")
        val body = FormBody.Builder()
            .add("key1","value1")
            .add("key2","value2")
            .build()

        val request = Request.Builder().url(URL)
            .post(body)
            .build()

        val call = client.newCall(request)

        val response = call.enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
               Log.e("OKHTTP","postAsync response:${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                Log.e("OKHTTP","postAsync response:${response.body?.string()}")
            }
        })

    }

    //post 异步请求 【多表单文件上上传】
    //在android高版本读取外部存储卡的文件都需要动态申请权限
//    fun postAsyncMultipart(context:Context) {
//        var file = File(Environment.getExternalStorageDirectory(),"1.png")
//        if(!file.exists()) {
//            Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show()
//            return
//        }
//
//       val body =  MultipartBody.Builder()
//            .addFormDataPart("key1","value1")
//            .addFormDataPart("key2","value2")
//            .addFormDataPart("file","file.png",RequestBody.create("application/octet-stream".toMediaType(),file))
//            .build()
//        val request = Request.Builder().url("接口是需要支持文件上传才可以使用的")
//            .post(body)
//            .build()
//
//        val call = client.newCall(request)
//        call.enqueue(object :Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("OKHTTP","postAsyncMultipart response:${e.message}")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                Log.e("OKHTTP","postAsyncMultipart response:${response.body?.string()}")
//            }
//        })
//    }

    //post异步请求提交string 请求方式
    fun postString() {
        val textPlan ="text/plain;charset=utf-8".toMediaType()
//        val application = "application/json;charset=utf-8".toMediaType()

        val jsonObj= JSONObject()  //上传json
        jsonObj.put("key1","value1")
        jsonObj.put("key2",100)

        val textObj = "username:username;password:password" //上传string

//        val body = RequestBody.create(application,jsonObj.toString())
        val body = RequestBody.create(textPlan,textObj)

        val request = Request.Builder().url("接口地址")
            .post(body)
            .build()
        val call  = client.newCall(request)
        call.enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP","postString response:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("OKHTTP","postString response:${response.body?.string()}")
            }
        })
    }
}