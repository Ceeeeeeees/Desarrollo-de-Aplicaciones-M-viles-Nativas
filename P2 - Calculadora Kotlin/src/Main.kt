fun main() {
    do {
        println("==============================================")
        println("=============\t Calculadora\t =============")
        println("==============================================")
        println("1. Suma \t")
        println("2. Resta \t")
        println("3. Multiplicacion \t")
        println("4. Division \t")
        println("5. Salir \t")
        println("Selecciona una opcion: \t")
        var opcionCalculadora: Int ?= readLine()!!.toInt()
        val opcionMenu = when (opcionCalculadora){
            1 -> {
                println("Ingrese el primer numero: \t")
                var numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("El resultado de la suma es: ${suma(numUno, numDos)}")
            }
            2 -> {
                println("Ingrese el primer numero: \t")
                val numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("El resultado de la resta es: ${resta(numUno, numDos)}")
            }
            3 -> {
                println("Ingrese el primer numero: \t")
                val numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("El resultado de la multiplicacion es: ${multiplicacion(numUno, numDos)}")
            }
            4 -> {
                println("Ingrese el primer numero: \t")
                val numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("El resultado de la division es: ${division(numUno, numDos)}")
            }
            else -> println("Opcion no valida")
        }
            print("Desea realizar otra operacion [S/N]? ")
            val opcionPrincipal = readLine()!!.toString().lowercase()
    } while (opcionPrincipal == "s")
}

fun suma(numUno: Double, numDos: Double): Double {
    return numUno + numDos
}
fun resta(numUno: Double, numDos: Double): Double {
    return numUno - numDos
}
fun multiplicacion(numUno: Double, numDos: Double): Double {
    return numUno * numDos
}
fun division(numUno: Double, numDos: Double): Double {
    return numUno / numDos
}

