class Slice {
    /*
    Returns a list containing elements at specified indices.
    */
    init {
        //Return a list of numbers in pos 0..2
        val slice = listOf(100,200,300,400,500,600,700,800,900).slice(0..2)
        Log.d("global","Slice: $slice") //[100, 200, 300]

        //Return a list of strings in index positions 0,1,5
        val slice2 = listOf("a","b","c","d","e","f").slice(listOf(0,1,5))
        Log.d("global","Slice2: $slice2") //[a, b, f]

        //Return a list of numbers in index positions 0,1,5
        val slice3 = listOf(1,2,3,4,5,6,7,8,9,10).slice(listOf(0,1,5))
        Log.d("global","Slice3: $slice3") //[1, 2, 6]

    }
}