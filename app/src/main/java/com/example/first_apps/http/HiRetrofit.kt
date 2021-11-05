package com.example.first_apps.http

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

object  HiRetrofit {
//    https://coding.imooc.com/class/ajaxbigdatacourserecommend
//    private val URL = "https://www.imooc.com/search/hotwords";
    private val client = OkHttpClient.Builder() //builder构造者设计模式
        .connectTimeout(10,TimeUnit.SECONDS) //连接超时时间
        .readTimeout(10,TimeUnit.SECONDS)  //读取超时
        .writeTimeout(10,TimeUnit.SECONDS) //写超时
        .addInterceptor(LoggingInterceptor())
        .build()

    private val retrofit:Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://coding.imooc.com")
        .addConverterFactory(GsonConverterFactory.create()) //数据转换适配器
        .build()

    fun <T> create(clazz: Class<T>):T {
        return retrofit.create(clazz)
    }
}

interface  ApiService {
//    @GET(value="/search/hotwords")
//    fun queryUser(@Query(value ="userId",encoded = true) userId:String): Call<UserResponse>

    @POST(value = "class/ajaxbigdatacourserecommend")
    fun getStudy(@Query(value="",encoded = true) userId: String):Call<StudyType>
}