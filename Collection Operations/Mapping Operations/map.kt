
class Map {
    /*
    Returns a list containing the results of applying the given transform function
    to each element of the original collection.
    */
    init {
        val map = listOf(10,20,30,40,50).map { it + 2 }
        Log.d("global", "Map: $map") //[12, 22, 32, 42, 52]

        val map2 = listOf("A","B","C").map { "-$it-" }
        Log.d("global", "Map2: $map2") //[-A-, -B-, -C-]

    }
}