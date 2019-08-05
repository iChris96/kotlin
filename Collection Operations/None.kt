class None {
    /*
    Returns true if no elements match the given predicate.
    */
    init {
        //none bigger than 10?
        val none = listOf(1, 2, 3, 4, 5, 6).none { it > 10 }
        Log.d("global", "None: $none") //true

        //none with module 2 equals to 0?
        val none2 = listOf(1, 2, 3, 4, 5, 6).none { it % 2 == 0 }
        Log.d("global", "None2: $none2") //false

        //none 16 in 0 to 15?
        val none3 = (0..15).none { it == 16 }
        Log.d("global", "None3: $none3") //true
    }
}