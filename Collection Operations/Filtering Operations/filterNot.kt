class FilterNot {
    /*
    Returns a list containing all elements not matching the given predicate.
    */
    init {
        val filterNot = listOf(1,2,3,4,5,6,7,8,9,10).filterNot { it % 2 == 0 }
        Log.d("global","FilterNot: $filterNot") //[1, 3, 5, 7, 9]

        val filterNot2 = listOf(1,2,3,4,5,6,7,8,9,10).filterNot { it == 1 }
        Log.d("global","FilterNot2: $filterNot2") //[2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
}