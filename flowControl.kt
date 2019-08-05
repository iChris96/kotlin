/* Control Flow */

    //IF
    var max:Int = 0
    var a:Int = 10
    var b:Int = 8

        //basic if
        if(a > b)
            max = a

        //basic if else
        if(a > b)
            max = a
        else
            max = b

        //As expression
        max = if (a > b) a else b

        //As expression with blocks
        max = if (a > b) {
            println("max = " + a)
            a
        } else {
            println("max = " + b)
            b
        }

    //FOR
        for(item in 1..4)
            println(item) //1,2,3,4

        for(item in 100 downTo 0 step 20)
            println(item) //100,80,60,40,20,0

        val elements = listOf("apple", "banana", "kiwifruit")
        for (index in elements.indices) {
            println("item at $index is ${items[index]}") //item at 0 is apple, item at 1 is banana, ...
        }

        for(i in elements.indices){
            println(array[i]) //apple,banana,kiwifruit
        }


    //WHEN
    var x = 5

        //basic when
        when(x){
            5 -> println("x == 5")
            6 -> println("x == 6")
            else -> println("x is neither 5 or 6")
        }

        when(x){
            5,6 -> println("x == 5 or x == 6")
            in 0...4 -> println("x is in the range")
            else -> println("x is not none of the above")
        }
