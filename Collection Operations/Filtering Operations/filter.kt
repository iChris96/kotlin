class Filter {
    /*
    Returns a list containing all elements matching the given predicate.
    */
    init {
        val filter = listOf(1,2,3,4,5,6,7).filter { it > 4 }
        Log.d("global", "Filter: $filter") //[5, 6, 7]

        val filter2 = listOf(1,2,3,4,5,6,7,8,9,10).filter { it % 2 == 0 }
        Log.d("global", "Filter2: $filter2") //[2, 4, 6, 8, 10]
    }
}