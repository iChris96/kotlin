
class SealedClass {
    init {
        val myKid = Human.Kid(8)
        val myBoy = Human.Boy(15)
        val myGirl = Human.Girl(20,"Seidi")

        Log.d("global","${myGirl.name}") //prints Seidi
        Log.d("global","${myGirl.age}") //prints Age

        myKid.whatIs() //prints is Kid
        myBoy.whatIs() //prints is Boy
        myGirl.whatIs() //prints is Girl and I got name: Seidi

        Log.d("global","${myBoy.isBoy}") //true
        Log.d("global","${myKid.isBoy}") //false

    }

}


sealed class Human{
    class Kid(val age: Int): Human()
    class Boy(val age: Int): Human()
    class Girl(val age: Int, val name: String): Human()

    val isBoy get() = this is Boy

    fun whatIs() =
        when(this){
            is Kid ->  Log.d("global","is Kid")
            is Boy ->  Log.d("global","is Boy")
            is Girl ->  Log.d("global","is Girl and I got name: ${this.name}")
            else -> Log.d("global","is other thing")
        }

}
