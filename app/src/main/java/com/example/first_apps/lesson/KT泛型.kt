package com.example.first_apps.lesson

import org.json.JSONObject

//kotlin泛型
fun main() {

    //1.泛型接口
    var drinkApple = DrinkApple()
    drinkApple.drink("drink")

    //2.泛型类
    var blueColor = BlueColor("wihte")
    blueColor.printColor()

    //3.泛型方法
    fromJson<String>("{}",String::class.java)

    //泛型约束
    fromJson2<JSONObject>("{}",JSONObject::class.java)

    //泛型约束3
    fromJson3<User>("{}",User::class.java)
}

interface Drink<T> {
    fun drink(t:T/*泛型字段*/)
}

class DrinkApple:Drink<String> {
    override fun drink(t:String) {
        println("drink:${t}")
    }
}

abstract class Color<T>(var t:T/*泛型字段*/) {
    abstract fun printColor()
}
class BlueColor(var color:String):Color<String>(color) {
    override  fun printColor() {
        println("printColor:${color}")
    }
}

fun <T> fromJson(json:String,tClass:Class<T>):T? {
    val instance:T? = tClass.newInstance()
    return instance;
}

// 所传递的类型T必须满足JSONObject类或JSONObject的子类
fun <T:JSONObject> fromJson2(json:String,tClass:Class<T>):T? {
    val instance:T? = tClass.newInstance()
    return instance;
}

//泛型类型限定-2
//所传递的类型T必须同时满足where子句的所有条件
//下例中类型T必须同时 满足JSONObject和Comparable
fun <T> fromJson3(json:String,tClass:Class<T>):T? where T:JSONObject,T:Comparable<T>{
    val instance:T? = tClass.newInstance()
    return instance
}
class User : JSONObject(),Comparable<User> {
    override  fun compareTo(other:User):Int {
        return 0
    }
}