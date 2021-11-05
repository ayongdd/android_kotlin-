package com.example.first_apps.lesson

import androidx.collection.arrayMapOf

//kotlin集合
/*1.List 有序列表，通过下标访问，可重复
  2.Set 元素唯一的集合，无序集合
  3.Map 是一组键值对 ，值可以重复 */
fun main() {

    //列表的创建方式 -- 可变列表
    var arrayString = mutableListOf<String>()
    arrayString.add("1")
    arrayString.add("2")
    arrayString.add("3")
    arrayString.add(3,"3");

    var arrayString2 = mutableListOf<String>("1","2","3","4")
    arrayString2.add("4")

    //列表的创建方式 -- 不可变列表
    //必须指定元素类型
    //必须指定初始化数据元素
    var arrayInt = listOf<Int>(1,2,3)

    //map字典的创建 -- 可变字典
    //字典是一组键值对，键是唯一，每个键刚好映射到一个值，值可以重复
    var array = arrayMapOf(Pair("key","value"))
    var arrayMap = mutableMapOf<String,String>()
    arrayMap["1"] = "1"
    arrayMap["2"] = "2"
    arrayMap["3"] = "3"
    arrayMap["3"] = "4" //此时会覆盖上面的"3"

    //map字典的创建 -- 使用Pair指定集合中初始化的元素
    var arrayMap2 = mutableMapOf<String,String>(Pair("key","value"))

    //map字典的创建 -- 不可变字典，不可动态添加，删除元素
    var arrayMap3 = mapOf<String,String>(Pair("key","value"))
    var arrayMap4 = mapOf<String,String>()

    //Set集合的创建 --可变集合
    val set = mutableSetOf<Int>()
    set.add(1)
    set.add(2)
    set.add(3)
    set.add(3) //添加不进去，Set集合不允许重复

    val set1 = mutableSetOf<Int>(1,2,3,4);

    //Set集合的创建 --不可变集合
    var set3 = setOf<Int>(1,2,3)

    //集合方法
    var arrayExamples = mutableListOf<String>("1","2","3","4","5","2")
    println("isEmpty:${arrayExamples.isEmpty()}") //false   //arrayExamples是否为空
    println("contains:${arrayExamples.contains("6")}") //false //是否包含元素6
    println("get:${arrayExamples[0]}") //获取元素
    println("indexOf:${arrayExamples.indexOf("2")}") //1 //获取元素2在集合中首次出现的位置，如果不在则返回-1 从0开始计数
    println("indexOf:${arrayExamples.lastIndexOf("5")}") //5  //获取元素2在集合中最后一次出现的位置，如果不在则返回-1 从1开始计数
    var iterator =arrayExamples.iterator()
    iterator.forEach { it->
        println(it) //"1","2","3","4","5","2"
    }

    arrayExamples.clear() //集合中的元素会被清除。isEmpty = true
    println("length:${arrayExamples.size}") //length:0

    arrayExamples.add(1,"7") //插入置顶元素
    arrayExamples[0] = "0" //修改对应元素 ,index不能超过size大小

    arrayExamples.removeAt(0)

    arrayExamples.reverse() //集合翻转
    arrayExamples.shuffle() //随机排列元素
    arrayExamples.sort() //排序，从小到大
    arrayExamples.sortDescending() //排序，从大到小
}