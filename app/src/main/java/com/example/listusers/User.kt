package com.example.listusers

class User(val name:String, val age:Int){
    override fun toString(): String {
        return "Имя: $name  Возраст: $age"
    }
}