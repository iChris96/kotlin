/* Functions */

//Stock function
    fun sum(num1:Int, num2:Int){
        var suma = num1 + num2
        println(suma)
    }

    //Return something function
    fun sum2(num1:Int, num2:Int):Int{
        return num1 + num2
    }

    //Return void function
    fun sum3(num1:Int, num2:Int):Unit{ //By defect stock functions are unit functions
        return
    }

    //Default functions
    fun sum4(num1:Int=1, num2:Int=1):Int{
        return num1 + num2 //returns 2 by defect
    }

    //Simple expresion functions
    fun square(n:Int) = n*n //returns n*n


    //Call functions
    var mySum = sum4(5,3) //normal call
    var mySum2 = sum4(num1 = 5, num2 = 3) //detailed call