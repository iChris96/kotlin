/* Member Functions */

    //String
    var cadena:String = "Hola a todos"
    cadena.get(0) //Returns Char -> H
    cadena.count() //Returns Int -> 12
    cadena.drop(3) //Returns String -> a a todos
    cadena.dropLast(5) //Returns String -> Hola a 
    var cadena2:String = "<<<Hola<<<"
    cadena2.dropWhile { !it.isLetter() } //Returns String -> Hola<<<
    cadena2.dropLastWhile { !it.isLetter() } //Returns String -> <<<Hola
    var cadena3:String = "255.100.50.0"
    cadena3.replace(".","[.]") //Returns -> 255[.]100[.]50[.]0