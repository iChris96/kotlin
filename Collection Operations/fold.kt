class Fold {

    /*
    fold takes an initial value, and the first invocation of the lambda you pass to it will receive that initial value 
    and the first element of the collection as parameters.
    (Int, Int) -> (Int)
    */

    init {
        
        //sum of all list numbers
        val fold = listOf(2, 2, 3).fold(0) { sum, element -> sum + element }
        Log.d("global","Fold: $fold") //(0+2)(2+2)(4+3) -> 7

        //fold 5 as return number
        val fold2 = listOf(5,10,20).fold(15){sum,actElement ->
            Log.d("global","sum: $sum")                 //15,5,5
            Log.d("global","actElement: $actElement")   //5,10,20
            5 //return number for next iteration as "sum"
        }
        Log.d("global","Fold2: $fold2") //5

        //sum of all list numbers
        val fold3 = listOf(20,20,20).fold(0){sum,actElement ->
            Log.d("global","sum: $sum")                 //0,20,40
            Log.d("global","actElement: $actElement")   //20,20,20
            sum + actElement
        }
        Log.d("global","Fold3: $fold3") //60

        //return the max number bigger than 10
        val fold4 = listOf(1, 6, 4).fold(10) { max, element ->
            if (element > max) element else max
        }
        Log.d("global","Fold4: $fold4") //10

        //return the max number bigger than 2
        val fold4 = listOf(1, 6, 4).fold(2) { max, element ->
            if (element > max) element else max
        }
        Log.d("global","Fold4: $fold4") //6
    }
}