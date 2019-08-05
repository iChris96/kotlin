/* Null */

var cadena = ""
cadena = null //error -> Object type String

var cadena2:String = null // error -> object type String

var cadena3 = null //good -> object type null

/*  Nulleables */

// Un String no puede ser nulo jamás
fun helloWorld(nombre: String) {
   println("Hola, " + nombre.trim())
}
 
// String? sí, pero el compilador obliga a comprobarlo antes de usarlo
fun helloWorldConIf(nombre: String?) {
   if (nombre != null) {
       println("Hola, " + nombre.trim())
   }
}
 
// Hay llamadas seguras, que devuelven null si la variable es null
// Y un operador Elvis, que devuelve un valor por defecto si lo de la izquierda es null
fun helloWorldLlamadaSegura(nombre: String?) {
   println("Hola, " + (nombre?.trim() ?: "mundo"))
}
 
// Por interoperabilidad, si un valor viene de Java se presupone nullable, pero el
// programador puede jurar que está seguro de que no... y si no es así, que se lance
// una NullPointerException
// Allá tú, si te parece buena idea
fun helloWorldPeligroso(nombre: String?) {
   println("Hola, " + nombre!!.trim())
}
