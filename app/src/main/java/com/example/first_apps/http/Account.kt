package com.example.first_apps.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun  main() {
    val json ="{\"uid\":\"001\",\"userName\":\"Freeman\",\"paddword\":\"password\",\"telNumber\":\"13000000000\"}"
    val gson = Gson()
//    fromJson(String json, Class<T> classOfT)
//    kotlin中传class对象写法 class名::class.java
    //字符串转对象
    val account:Account = gson.fromJson<Account>(json,Account::class.java)
    println("fromJson:${account.toString()}")

    //对象转化成json
    val accountJson = gson.toJson(account)
    println("toJson:${accountJson}")

    //json转化成集合
    val jsonList ="[{\"uid\":\"001\",\"userName\":\"Freeman\",\"paddword\":\"password\",\"telNumber\":\"13000000000\"}]"
    val accountList = gson.fromJson<List<Account>>(jsonList,object:TypeToken<List<Account>>() {}.type)
    println("fromJson to List:${accountList.size}")

    //集合转化成list
    val accountJsonLit = gson.toJson(accountList)
    println("list to json:${accountJsonLit}")

    val userModelJson = "{\"result\":0,\"data\":[{\"word\":\"Java\\u5de5\\u7a0b\\u5e08\",\"is_default\":\"1\"},{\"word\":\"Vue\",\"is_default\":\"0\"},{\"word\":\"Python\",\"is_default\":\"0\"},{\"word\":\"Go\",\"is_default\":\"0\"},{\"word\":\"SpringBoot\",\"is_default\":\"0\"},{\"word\":\"Docker\",\"is_default\":\"0\"},{\"word\":\"React\",\"is_default\":\"0\"},{\"word\":\"\\u5c0f\\u7a0b\\u5e8f\",\"is_default\":\"0\"}],\"msg\":\"\\u6210\\u529f\"}"
    val userResponse =  gson.fromJson<UserResponse>(userModelJson,UserResponse::class.java)
    println("userResponse:${userResponse}")
}

//kotlin 声明数据模型可以不给初始值
data class Account2 (
    val uid:String = "",
    val userName:String,
    val password:String,
    val telNumber:String
)

class Account {
    val uid:String = "0001"
    val userName:String = "Freeman"
    val password:String = "password"
    val telNumber:String = "13000000000"

    override fun toString(): String {
        return "Account(uid='$uid',useName='$userName',password='$password',telNumber='$telNumber')"
    }
}
data class UserResponse(
    val data: List<Data>,
    val msg: String,
    val result: Int
)

data class Data(
    val is_default: String,
    val word: String
)


data class StudyType(
    val data: List<StudyData>,
    val msg: String,
    val result: Int
)

data class StudyData(
    var app_cover_pic: String,
    var cat_id: String,
//    val chapter_media: String,
//    val comment_num: String,
//    val comment_score: String,
//    val description: String,
//    val duration: String,
//    val duration_fmt: String,
//    val easy_type: String,
//    val end_time: String,
//    val extra: String,
//    val finished: String,
//    val id: String,
//    val manual_time: String,
//    val max_chapter: String,
//    val max_media: String,
    var name: String,
//    val numbers: String,
//    val online_time: String,
//    val pic: String,
//    val pic2: String,
//    val pic2_url: String,
//    val pic_url: String,
//    val price: String,
//    val renovate: String,
    var short_description: String
//    val show_price_info: ShowPriceInfo,
//    val start_time: String,
//    val status: String,
//    val update_in_24hours: Boolean,
//    val update_new: Boolean,
//    val update_time: String,
//    val update_timepoint: Int,
//    val video_id: String
)

data class ShowPriceInfo(
    val buy_dist_income: Int,
    val buy_dist_ratio: String,
    val discount_end_time: String,
    val discount_name: String,
    val discount_price: Int,
    val discount_time: Int,
    val discount_type: Int,
    val dist_income: Int,
    val dist_ratio: String,
    val in_discount_time: Int,
    val ios_price: String,
    val is_show_discount_price: Int,
    val is_show_price: Int,
    val open_countdown: String,
    val price: String
)