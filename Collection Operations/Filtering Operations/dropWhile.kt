
class DropWhile {
    /*
    Returns a list containing all elements except first elements that satisfy the given predicate.

    Drop while the predicate is satisfactory
    */
    init {
        val dropWhile = listOf(1,2,3,4,5,6,7).dropWhile { it < 5 }
        Log.d("global","DropWhile: $dropWhile") //[5, 6, 7]

        val dropWhile2 = listOf(100,100,200,100,400).dropWhile { it == 100}
        Log.d("global","DropWhile: $dropWhile2") //[200, 100, 400]
    }
}