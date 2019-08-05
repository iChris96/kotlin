class Reduce {
    /*
    Reduce doesn't take an initial value, but instead starts with the first element of the collection as
    the accumulator.

    You can use reduce when your operation does not depend on any values other than those in the
    collection you're applying it to.
    (Int, Int) -> (Int)
    */

    init {
        val reduce = listOf(1, 2, 3).reduce { sum, element -> sum + element }
        Log.d("global", "Reduce: $reduce") //(1+2)(3+3) -> 6

        val reduce2 = listOf(1,2,3,4,5).reduce { sum, element ->
            Log.d("global", "Sum: $sum")            //1,3,6,10
            Log.d("global", "Element: $element")    //2,3,4,5
            sum + element
        }
        Log.d("global", "Reduce2: $reduce2") //15
    }
}