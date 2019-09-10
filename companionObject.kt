/* Singleton object inner into some class */
class CompanionObject {
    init {
        ClassA.showFromCompanion() //Hello I am a companion object
        Log.d("global","${ClassA.mVal}") //5

        val myA = ClassA() //new ClassA instance, Only has access to .showFromA() method
        myA.showFromA() //I am A instance, mVal is: 5

        ClassA.mVal = 7 //change mVal value for ever
        Log.d("global","${ClassA.mVal}") //7
        myA.showFromA() //I am A instance, mVal is: 7

        val myA2 = ClassA()//A new second instance
        myA2.showFromA()//I am A instance, mVal is: 7

        val x = ClassA.Companion //If you companion object hasn't name , otherwise x = A.MyCompanion1
        x.showFromCompanion() //Hello -> same as A.showFromCompanion()
        Log.d("global","${x.mVal}") //7 -> same as A.mVal
    }
}


class ClassA{
    companion object{
        var mVal = 5

        fun showFromCompanion(){
            Log.d("global", "Hello I am a companion object")
        }
    }

    fun showFromA(){
        Log.d("global", "I am A instance, mVal is: $mVal")
    }
}