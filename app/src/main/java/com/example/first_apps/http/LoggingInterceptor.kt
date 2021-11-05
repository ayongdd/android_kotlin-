package com.example.first_apps.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

class LoggingInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val time_start = System.nanoTime()
        val request = chain.request()
        val response = chain.proceed(request)

        //将结果转化成buffer字符串
        val buffer = Buffer()
        request.body?.writeTo(buffer)
        val requestBodyStr =  buffer.readUtf8()

        Log.e(
            "OKHTTPP",
            String.format("Sending request %s with params %s",request.url,requestBodyStr)
        )

//        string() 只能调用一次
        val bussinessData = response.body?.string()?:"response body is null"
        val mediaType = response.body?.contentType()
        val newBody = ResponseBody.create(mediaType,bussinessData)
        val newResponse = response.newBuilder().body(newBody).build()

        val time_end = System.nanoTime()
        Log.e( //打印 计算请求时间
            "OKHTTP",
             String.format("Received response for %s in %.1fms >>> %s",
             request.url,(time_end-time_start)/1e6,bussinessData))
        return  newResponse
    }
}