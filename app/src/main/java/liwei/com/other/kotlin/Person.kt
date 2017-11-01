package liwei.com.other.kotlin

open class Person(var name : String,var age : Int,var weight : Float){

}

class Student (name : String,age : Int ,weight : Float,var score : Double) : Person(name,age, weight)

