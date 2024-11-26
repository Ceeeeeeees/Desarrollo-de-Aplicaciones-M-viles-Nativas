/**
 * Función principal que ejecuta la calculadora
 *
 * @author Saucedo Moreno César Enrique
 * @carnet 
 * @group 7CV1
 * @date 11-Septiembre-2024
 * @version 1.0
 */
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
        val opcionMenu = when (opcionCalculadora){ // Se puede usar un when para asignar el valor de la variable (opcionMenu) dependiendo de la condición
            //Similar a un switch en otros lenguajes
            1 -> {
                println("Ingrese el primer numero: \t")
                var numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("La operacion a realizar es: $numUno + $numDos")
                println("\nEl resultado es: \t${suma(numUno, numDos)}")
            }
            2 -> {
                println("Ingrese el primer numero: \t")
                val numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("La operacion a realizar es: $numUno - $numDos")
                println("\nEl resultado es: \t${resta(numUno, numDos)}")
            }
            3 -> {
                println("Ingrese el primer numero: \t")
                val numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("La operacion a realizar es: $numUno * $numDos")
                println("\nEl resultado es: \t${multiplicacion(numUno, numDos)}")
            }
            4 -> {
                println("Ingrese el primer numero: \t")
                val numUno = readLine()!!.toDouble()
                println("Ingrese el segundo numero: \t")
                val numDos = readLine()!!.toDouble()
                println("La operacion a realizar es: $numUno / $numDos")
                println("\nEl resultado es: \t${division(numUno, numDos)}")
            }
            else -> println("Opcion no valida")
        }
            print("Desea realizar otra operacion [S/N]? ")
            val opcionPrincipal = readLine()!!.toString().lowercase()
    } while (opcionPrincipal == "s")
}
/**
 * Función que realiza la suma de dos números.
 *
 * @param numUno El primer número a sumar.
 * @param numDos El segundo número a sumar.
 * @return El resultado de la suma de los dos números.
 */
fun suma(numUno: Double, numDos: Double): Double {
    return numUno + numDos
}
/**
 * Función que realiza la resta de dos números.
 *
 * @param numUno El primer número.
 * @param numDos El segundo número.
 * @return El resultado de la resta.
 */
fun resta(numUno: Double, numDos: Double): Double {
    return numUno - numDos
}
/**
 * Función que realiza la multiplicación de dos números.
 *
 * @param numUno El primer número.
 * @param numDos El segundo número.
 * @return El resultado de la multiplicación.
 */
fun multiplicacion(numUno: Double, numDos: Double): Double {
    return numUno * numDos
}
/**
 * Función que realiza la división de dos números.
 *
 * @param numUno El primer número.
 * @param numDos El segundo número.
 * @return El resultado de la división.
 */
fun division(numUno: Double, numDos: Double): Double {
    return numUno / numDos
}

/**
 *
 * SMCE - 2021630676
 *
 */
