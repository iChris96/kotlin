class Any {
    /*
    Returns true if at least one element matches the given predicate.
    (Any) -> (Boolean)
    */
    init {
        //any bigger than 10?
        val any = listOf(1, 2, 3, 4, 5, 6).any { it > 10 }
        Log.d("global", "Any: $any") //false

        //any with module 2 equals to 0?
        val any2 = listOf(1, 2, 3, 4, 5, 6).any { it % 2 == 0 }
        Log.d("global", "Any2: $any2") //true

        //any 16 in 0 to 15?
        val any3 = (0..15).any { it == 16 }
        Log.d("global", "Any3: $any3") //false

    }
}