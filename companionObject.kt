class A{
    companion object{
        var mVal = 5

        fun show(){
            println("Hello")
        }
    }

    fun showFromA(){
        println("Hello from A instance, mVal = $mVal")
    }
}

class CompanionObject {
    init {
        A.show() //Hello
        println(A.mVal) //5

        val myA = A() //A new instance, Only has access to .showFromA() method
        myA.showFromA() //Hello from A instance, mVal = 7

        A.mVal = 7 //chance mVal value for ever
        println(A.mVal) //7
        myA.showFromA() //Hello from A instance, mVal = 7

        val myA2 = A()//A new second instance
        myA2.showFromA()//Hello from A instance, mVal = 7

        val x = A.Companion //If you companion object hasn't name , otherwise x = A.MyCompanion1
        x.show() //Hello -> same as A.show()
        println(x.mVal) //7 -> same as A.mVal


    }
}