class Invoke {
    init {
        val hello = Greeter("Hello") //Overloading
        hello("world")  //Invoke way -> Prints "Hello world!"
        hello.defaultGreeting() //Normal way ->  Prints "Hello somebody!"
    }
}

class Greeter(private val greeting: String) {
    operator fun invoke(target: String) = Log.d("global","$greeting $target!")
    fun defaultGreeting() = Log.d("global","$greeting Somebody!")
}