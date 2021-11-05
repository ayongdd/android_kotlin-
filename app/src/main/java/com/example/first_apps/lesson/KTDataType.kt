package com.example.first_apps.lesson

//kotlin 数据类型
fun main() {

    //1.如何去声明一个基本数据类型的变量。有哪些方式？
    //整型默认为int 如果超过int的最大值则推断为Long
    var number = 100;

    //在赋值的数字后面加上L，会自动推断为Long类型
    var longNumber = 20L;

    //在变量后面+ ：+数据类型  直接声明这个类型的变量
    var byteNumber:Byte  = 1;

    //浮点类型
    //编辑器会根据赋值推断数据类型（默认double类型，小数点后超过6为声明double类型,反之声明float）
    var doubleNumber = 3.1414565654;
    var floatNumber = 3.14566665454f; //尾部加上f或F表示这是一个Float类型的浮点数

    println("doubleNumber:"+ doubleNumber);
    println("floatNumber:"+floatNumber);


    //字符类型
    //赋值的时候用单引号
    var char:Char = '0';

    //布尔类型，
    // 使用Boolean 来声明类型，只有ture/false
    var bool:Boolean = true;

    //字符串类型
    var str:String = "12345566"; //从0开始截取
    //字符串取值
    var strNumber:Char = str[1]; //2  截取之后变成Char类型

    println(strNumber) //2

    //字符串模板表达式
    println("the result is $str"); //the result is 12345566
    println("the result is ${str.length}") //the result is 8
    //拼接
    println("im" + 10+"years old!");

    //"""""" 分解符（三个双引号） 等意于js的``

    // 数据类型间的强制转换
    var number1 = 100;
    number1.toString()
    number1.toByte()
    number1.toShort()
    number1.toLong()
    number1.toFloat()

    //数据加减乘除
    //整数除以整数结果还是返回整数 1
    var numberInt:Int = 3/2;
    println("number:$numberInt"); //1

    //整数的3除以小数2，结果是小数1.5
    var numberDouble:Double = 3/2.toDouble();
    println("number:$numberDouble"); //1.5

    //位运算
    var vip = true;
    var admin = false;

    //与操作，要求两个都满足结果才返回true
    var result:Boolean = vip.and(admin); //false
    //或操作，只要有一个条件是true ,结果就会返回true
    var result2:Boolean = vip.or(admin); //true

}