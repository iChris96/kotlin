class InAndOutTypes {
    init {
        val a = ParameterizedClass(5)
        Log.d("global","a value: ${a.getValue(1)}")
        val aa = ParameterizedClass("Five")
        Log.d("global","aa value: ${aa.getValue("One")}")

        val out = ParameterizedProducer<Int>(4)
        Log.d("global","out value: ${out.get()}")
        val out2 = ParameterizedProducer("Four")
        Log.d("global","out2 value: ${out2.get()}")

        val myIn = ParameterizedConsumer<Int>()
        Log.d("global","myIn value: ${myIn.toString(100)}")
        val myIn2 = ParameterizedConsumer<Boolean>()
        Log.d("global","myIn2 value: ${myIn2.toString(false)}")
    }
}

//Generic as in & out
class ParameterizedClass<A>(private val value: A) {

    fun getValue(param: A): A {
        return value
    }
}

//Generic as out only
class ParameterizedProducer<out T>(private val value: T) {
    fun get(): T {
        return value
    }
}

//Generic as in only
class ParameterizedConsumer<in T> {
    fun toString(value: T): String {
        return value.toString()
    }

    //fun some(): T{} //Error!
}