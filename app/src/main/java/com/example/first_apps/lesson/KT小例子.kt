package com.example.first_apps.lesson

import java.lang.Exception

//一次四则运算表达式计算器
fun main() {
    while (true) {
        println("==========请输入你的表达式==========")
        var input = readLine()
        try {
            input?.let {
                //判空操作
                var ret = calculate(it)
                println("${input}=${ret}")
                println("是否继续使用(y/n)")
                var cmd = readLine()
                cmd?.let {
                    if(it.equals("n")) { //选择n
                        System.exit(-1) //退出程序
                    } else {

                    }
                }
            }
        }catch (ex:Exception) {
            ex.printStackTrace()
        }
    }
}

fun calculate(input: String): String {

    //input = 1+1
    //input = 1-10
    //input = 1/2
    //input = 1*11

    if(input.contains("+")) {
        var nums = input.trim().split("+")
        return  operate(nums[0].toDouble(),nums[1].toDouble(),"+").toString()
    }else if(input.contains("-")) {
        var nums = input.trim().split("-")
        return  operate(nums[0].toDouble(),nums[1].toDouble(),"-").toString()
    } else if(input.contains("/")) {
        var nums = input.trim().split("/")
        return  operate(nums[0].toDouble(),nums[1].toDouble(),"/").toString()
    } else if(input.contains("*")) {
        var nums = input.trim().split("*")
        return  operate(nums[0].toDouble(),nums[1].toDouble(),"*").toString()
    }else {
        return "error:你输入的表达式有误"
    }
}

fun operate(num1: Double, num2: Double,operate:String): Double {
    return when(operate) {
        "+" -> num1+num2
        "-"->num1-num2
        "/"->num1/num2
        "*"->num1*num2
        else->0.0
    }
}
