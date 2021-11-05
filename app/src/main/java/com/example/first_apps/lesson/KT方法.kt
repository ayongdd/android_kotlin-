package com.example.first_apps.lesson

//kotlin Lambda
fun main() {
    var numbers = arrayOf(1,2,3,4);
    transform(numbers,action={index,element->
        index*element //闭包中最后一行表达式，计算后的结果就是action函数的返回值
    })

    for(number in numbers) {
        println("number:${number}")
    }

    //lambda隐形参数it
    numbers.forEach {
        println(it)
//        0
//        2
//        6
//        12
    }



}

//lambda表达式作为方法中的参数的时候，定义transform方法，可以对数组中的元素进行变换
//array:要求传入一个数组，元素类型为Int整数类型
//action:要求传入的是一个方法，接受两个参数下标index,元素element.要求返回变换后的元素，会替换掉老的元素
fun transform(array:Array<Int>,action:(index:Int,elemnt:Int)->Int) {
    for(index in array.indices ) {
        val newValue = action(index,array[index])
        array[index] = newValue
    }
}

