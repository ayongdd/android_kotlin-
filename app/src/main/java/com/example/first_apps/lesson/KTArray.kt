package com.example.first_apps.lesson

import org.json.JSONObject

//kotlin容器
fun main() {
    //kotlin数组
    //1.使用arrayOf创建数组，必须制定数组的元素，可以是任性类型
    var arrayNumber:Array<Int> = arrayOf(1,2,3,4);
    //集合中的元素可以是任意类型
    //kotlin中的Any等于java中的object 对象的意思
    var arrayObjects:Array<Any> = arrayOf(1,true,"2")

    //2.使用arrayOfNulls创建空数组，
     //创建 一个指定大小的、所有元素都为空的数组，但必须制定集合中的元素类型
    var arrayOfNulls:Array<String?> = arrayOfNulls<String>(5) //5指的是数组长度 ?代表可空，可以为null的意思
    arrayOfNulls[0] = "element1"
    arrayOfNulls[1] = "element2"
    arrayOfNulls[2] = "element3"
    arrayOfNulls[3] = "element4"
    arrayOfNulls[4] = null

    //利用array的构造函数，动态的创建数组
    //创建一个Array<String> 初始化为["0","1","4","9","16"]
    //i = 0,1,2,3,4
    //i*i = "0","1","4","9","16"
    //数组创建的时候会循环五次，i就是数组的下标
    //->右边的表达式的结果，就是数组中当前下标的元素
    var asc:Array<String> = Array(5) {i:Int->(i*i).toString()}

    //4.字节数组
    var bytes = ByteArray(5);
    bytes[0] = 0;

    //5.使用IntArray创建数组
    var intArray2 = IntArray(5);
    intArray2[0] = 1;

    //创建一个长度为5的值全为100的IntArray[100,100,100,100,100]
    var intArr2 = IntArray(5) {100}

    //输出 [0,2,4,6,8]
    var intArr3 = IntArray(5) {i->i*2} //等价于下面
    var intArr4 = IntArray(5) {it*2} //it指的是数组下标

    //数组遍历
    for(i in  intArr2.indices) {
        println(intArr2[i]);
//        100
//        100
//        100
//        100
//        100
    }

    intArr2.forEach {it->
        println("$it")
//        100
//        100
//        100
//        100
//        100
    }

    intArr2.forEachIndexed { index,item->
        println("$index->$item");
//        0->100
//        1->100
//        2->100
//        3->100
//        4->100
    }
}