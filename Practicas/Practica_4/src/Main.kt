/*
* Practica 4 -  Programación Orientada a Objetos
* @autor :  Saucedo Moreno César Enrique
* @fecha :  24 de SEptiembre de 2024
* @version 1.0
* */

class Car{
    var type: String?=null
    var maxspeed: Int?=null
    var numberofSeats: Int?=null
}

open class Student (name: String , college: String , age: Int){
    var name: String ?= name
    var college: String ?= college
    var age: Int ?= age
}
class Teacher (name: String , college: String , age: Int) : Student(name, college, age)
abstract class ComputerCourse {
    abstract fun coursePrice()
    abstract fun coursePrerequisitos()
}
class languageCourse (): ComputerCourse() {
    override fun coursePrice() {
        println("Precio del curso :")
    }
    override fun coursePrerequisitos() {
        println("Prerequisitos del curso :")
    }
}
interface Calc {
    fun sum(x: Int, y: Int)
    fun multiplicacion(a: Int , z: Int)
}
class Math : Calc {
    override fun sum(x: Int, y: Int) {
        var sumtotal = x + y
        println("La suma de $x + $y = $sumtotal")
    }
    override fun multiplicacion(a: Int, z: Int) {
        var multi = a * z
        println("La multiplicación de $a * $z = $multi")    }
}
class Math2 : Calc {
    override fun sum(x: Int, y: Int) {
        var multi = x * y
        println("La suma de $x + $y = $multi")
    }
    override fun multiplicacion(a: Int, z: Int) {
        var multi = a * z
        println("La multiplicación de $a * $z = $multi")
    }
}
// Ambas son correctas para definir una clase generica
// Esta primera nos permite definir en un inicio el tipo de dato que se va a utilizar
// class Permission(userName: String , password: String)
class Permission <T> {
    var userName: T ?= null
    var password: T ?= null
    var ID: T ?= null
}
// Esta segunda nos permite definir el tipo de dato en el momento de la creación del objeto
class Juego{
    companion object {
        val juegosJugados = 10
    }
}
open class Avion() {
    var tipo: String?=null
    var capacidad: Int?=null
}
class Bus():Avion()
var volvo_bus = Bus()

fun main (){
    var myLista = mutableListOf <Any> (1 , "Cesar Saucedo" , 500.52)
    myLista [0] = 200
    for (index in 0..myLista.size-1){
        println(myLista[index])
    }
}
