package me.com.testtinker

import java.lang.Integer.parseInt

fun testUtil(a:String,b:String,c:Int):Unit{
    print(a+b+c)
}

fun vars(vararg a:Int){
    for (aa in a){
        print(aa)
        print("\n")
    }
}

fun lambda(){
    val aa:(Int,Int)->Int={x,y->x-y}
    println(aa(23,43))
}

fun str(){
    var a = 1
// 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
// 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s1+"\n")
    println(s2)
}

fun nul(){
    //类型后面加?表示可为空
    var age: String? = "2"
//抛出空指针异常
    val ages = age!!.toInt()
//不做处理返回 null
    val ages1 = age?.toInt()
//age为空返回-1
    val ages2 = age?.toInt() ?: -1

    println(ages)
    println(ages1)
    println(ages2)
}


fun nul1(args: Array<String>) {
//    if (args.size < 2) {
//        print("Two integers expected")
//        return
//    }
    val x = parseInt(args[0])
    val y = parseInt(args[1])
    // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
    if (x != null && y != null) {
        // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
        print(x * y)
    }
}