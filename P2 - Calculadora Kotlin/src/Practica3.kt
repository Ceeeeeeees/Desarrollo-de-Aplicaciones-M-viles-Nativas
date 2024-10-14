/**
 * Función principal que las funciones y ejercicios de la práctica 3.
 *
 * @author Saucedo Moreno César Enrique
 * @carnet 2021630676
 * @group 7CV1
 * @date 17-Septiembre-2024
 * @version 1.0
 */
var myCompania = "Cesar7CV1"

fun main () {
    var exUno = 10
    ejercicioUno(exUno)
    var exDos = 10
    ejercicioDOs(exDos)
    var exTres = 80
    ejercicioTres(exTres, null)
    var exTres_2 = 50
    ejercicioTres(exTres_2, null)
    var exCuatro = 16
    var exCuatro_2 = 1998
    ejercicioCuatro(exCuatro, exCuatro_2)
    println("==============================================")
    println("====\tEjercicio 5 Pizza\t====")
    println("""Selecciona el tamaño de la pizza : 
         1-> Chica 
         2-> Mediana 
         3-> Grande """")
    var exCinco = readLine()!!.toInt()
    var exCinco_2 : Int = 0
    ejercicioCinco(exCinco, exCinco_2)
    ejercicioSeis()
    ejercicioSiete()
    ejercicioOcho()
    ejercicioNueve()
    ejercicioDiez()
    ejercicioOnce()
    ejercicioDoce()
    ejercicioTrece()
    var exCatorce = 10
    var exCatorce_2 = 20
    ejercicioCatorce(exCatorce, exCatorce_2)
    var resultadoQuince = ejercicioQuince(50 , 100)
    println("El resultado de la suma es : $resultadoQuince")
    name("Ford vs Ferrari")
}

fun ejercicioUno (variableEx1 : Int ){
    if (variableEx1 > 30)
        println("Hello, I am If statement running now.....")
    println("The End")
    println("Fin del ejercicio 1")
}

fun ejercicioDOs (variableEx2 : Int ){
    if (variableEx2 > 30)
        println("Hello, I am If statement running now.....")
    else
        println("Hello, I am Else statement running now.....")
    println("Fin del ejercicio 2")
}

fun ejercicioTres (variableEx3 : Int , variable_2Ex3 : String ?) {
    var variable_2Ex3 = variable_2Ex3
    if (variableEx3 >=90) variable_2Ex3 = "Grado A"
    else if (variableEx3 >=80) variable_2Ex3 = "Grado B"
    else if (variableEx3 >=70) variable_2Ex3 = "Grado C"
    else if (variableEx3 >=60) variable_2Ex3 = "Grado D"
    else variable_2Ex3 = "Grado F"
    println(variable_2Ex3)
    println("Fin del ejercicio 3")
}

fun ejercicioCuatro (variableEx4 : Int , variable_2Ex4 : Int){
    if(variableEx4 >= 18 || variable_2Ex4 >= 1998) println("He is Authorized")
    else println("He is not Authorized")
    println("Fin del ejercicio 4")
}

fun ejercicioCinco (variableEx5 : Int , variable_2Ex5 : Int){
    var variable_2Ex5 = variable_2Ex5
    when (variableEx5){
        1-> variable_2Ex5 = 5
        2-> variable_2Ex5 = 7
        3-> variable_2Ex5 = 9
        else -> println("NO seleccionaste el tamaño correcto")
    }
    println("El precio total es : $variable_2Ex5 USD")
    println("Fin del ejercicio 5")
}

fun ejercicioSeis(){
    for (x in 0..5){
        println("El valor de x es : $x")
    }
    println("Fin del ejercicio 6")
}

fun ejercicioSiete(){
    for (x in 0..5){
        println("El valor de x es : $x")
        if (x == 3) break
    }
    println("Fin del ejercicio 7")
}

fun ejercicioOcho (){
    var y = 10
    for (x in 0 ..y){
        println("El valor de x es : $x")
    }
    println("Fin del ejercicio 8")
}

fun ejercicioNueve(){
    var x = arrayOf(10, 20, 30, 40, 50)
    for (index in 0..4){
        println("El valor de x es : ${x[index]}")
    }
    println("Fin del ejercicio 9")
}

fun ejercicioDiez(){
    var x = 1
    while (x <= 5){
        println("El valor de x es : $x")
        x++
    }
    println("Fin del ejercicio 10")
}

fun ejercicioOnce(){
    var x = 1
    do {
        println("El valor de x es : $x")
        x++
    } while (x <= 5)
    println("Fin del ejercicio 11")
}

fun ejercicioDoce (){
    var x = 1
    do {
        println("El valor de x es : $x")
        x++
        if (x == 3) break
    } while (x <= 5)
    println("Fin del ejercicio 12")
}

fun ejercicioTrece () {
    var x = 0
    do {
        x++
        if (x == 3) continue
        println("El valor de x es : $x")
    } while (x <= 5)
    println("Fin del ejercicio 13")
}

fun ejercicioCatorce(x : Int , y : Int){
    var z = x + y
    println("La suma de $x y $y es : $z")
    println("Fin del ejercicio 14")
}

fun ejercicioQuince(x : Int , y : Int): Int {
    var z = x + y
    println("La suma de $x y $y es : $z")
    println("Fin del ejercicio 14")
    return z
}

fun name (myCompania : String){
    println("Mi compañia es : $myCompania")
}
