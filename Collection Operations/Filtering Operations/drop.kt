class Drop {
    /*
    Returns a list containing all elements except first n elements.
    */
    init {
        
        val drop = listOf(1,2,3,4,5,6,7).drop(2)
        Log.d("global", "Drop: $drop") //[3, 4, 5, 6, 7]

    }
}