package liwei.com.other.kotlin

fun main(args : Array<String>){
    println("Hello World")

    val number = 5
    val price  = 20.1f
    val name  = "大米"

    println("产品：$name ,单价：$price ,数量：$number ,总计：${price * number}")

    for(x in 1..5){
        println(x)
    }

    val a = 1
    val b = 2
    val c = if(a > b) a else b
    println("c 的值是：$c")

    val d = 5
    if(d in 1..9){
        println("d在区间中")
    }

    println("--------------------------------------------")

    val arr1 : IntArray = intArrayOf(1,2,3)
    for((index,value) in arr1.withIndex()){
        println("下标：$index 的值是：$value")
    }

    println("--------------------------------------------")

    val arr2 = listOf<String>("a","b","c","d")
    for(index in arr2.indices){
        println("下标：$index 的值是：${arr2[index]}")
    }

    println("--------------------------------------------")

    val stu  = Student("liwei",22,177.4f,125.4)
    println("姓名：${stu.name} ，年龄：${stu.age} ，体重：${stu.weight} ，分数：${stu.score}")
}