package com.example.first_apps.lesson

fun  main() {
    // .. 包含闭合 而 until不包含闭合
    for (i in 1..10) {//遍历区间，注意Kotlin的区间的包含或是闭合的。
        print("$i ")
    }
//输出结果: 1 2 3 4 5 6 7 8 9 10

    for (i in 1 until 10) {
        print("$i ")
    }
//输出结果: 1 2 3 4 5 6 7 8 9s


    for (i in 10 downTo 1 step 2) {
        print("$i ")
    }
//输出结果: 10 8 6 4 2

    val items = listOf("java", "kotlin", "android")
    var index = 0
    while (index < items.size) {//while 循环的遍历方式
        println("The $index lang is ${items[index++]}")
    }

    fun <T> MutableList<T>.swap1(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    val test2 = mutableListOf("Android Q", "Android N", "Android M")
    test2.swap1(0,1)
    println("============")
    println(test2)

    fun testApply() {
        ArrayList<String>().apply {
            add("testApply")
            add("testApply")
            add("testApply")
            println("$this")
        }.let { println(it) }
    }

    testApply()
}
