class Count {
    /*
    Returns the number of elements matching the given predicate.
    (Any) -> (Int)
    */
    init {
        val count = listOf(1,2,3,4,5,6,7,8,9).count { it == 5 }
        Log.d("global","Count: $count") //1

        val count2 = listOf(10,20,20,30,40,50).count{ it > 15 && it < 35}
        Log.d("global","Count2: $count2") //3

        val count3 = listOf("abcd","abcde","abcde","abcdef").count { it.length > 4 }
        Log.d("global","Count3: $count3") //3

    }
}