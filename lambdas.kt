class lambdas {

    //Firsts executed
    val z = 90
    val x4 =  fun (num:Int):Int { return num * 4}
    val x5 =  {num:Int -> num * 5} //lambda
    val x6: (Int) -> Int =  {num:Int ->  num* 6} //lambda

    //Init Second Executed
    init {
        val x = 50
        val y = 70  + z
        println(x + y)
        //Nivel 1
        addTwoNum(40, x2(70))
        addTwoNum(40, x3(70))
        addTwoNum(40, x4(3))
        //Nivel 2
        addTwoNum(40, x5(70)) //lambda
        addTwoNum(40, x6(90)) //lambda
        //Nivel 3
        addTwoNum2(40,x5) //lambda as argument
        addTwoNum2(40,x6) //lambda as argument
        //nivel 4
        addTwoNum2(50){it -> //lamda implicit
            it + 1 //displays 91 (50 + 40(it) + 1)
        }
        addTwoNum2(50){
            5 //displays 50 + 5
        }
    }

    //Keep firsts executed
    fun addTwoNum(num1:Int, num2: Int){
        println(num1+num2) //print num1 + num 2
    }

    //Using lambda as argument
    fun addTwoNum2(num: Int, mFunctionNum2: (Int) -> Int)
    {
        val num2 = mFunctionNum2(40) //invoke the function here
        println(num+num2)
    }

    fun x2(num:Int): Int {
        return num *2
    }

    fun x3(num:Int) = num * 3



}