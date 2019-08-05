class HightOrderStandarFunctions {

    init {
        val x = 5

        //No receiver
        run{
            x + 1 //-> this return some Int
            //"Awesome" //-> this return some string
        }.printlnSomeInt() //prints 6

        //With receiver
        with(x){
            this + 2
        }.printlnSomeInt() //prints 7

        //better way than with
        x.run {
            this + 3 //returns some int
        }.printlnSomeInt() //prints 8

        val mPerson = Person("Juan",15)

        mPerson.run {
            //this.age + 1
            age += 1 //better way
            age //return new actual age
        }.printlnSomeInt()//prints 16

        //this use a modifier it
        mPerson.let { it
            it.age += 1 //add 1 year to mPerson age
            it.age //return new actual age
        }.printlnSomeInt()//prints 17

        //Ctrl + Q for documentation

        //apply returns the object
        mPerson.apply {
            age += 1
            name = "Carlos"
        }.showMyPerson() //Person(name=Carlos, age=18)

        /*
            apply is better that
            mPerson.age += 1
            mPerson.name = "Carlos"
        */

        //also returns the object and uses a modifier
        mPerson.also {
            it.age += 1
            it.name = "Bryan"
        }.showMyPerson() //Person(name=Bryan, age=19)

    }
}

fun Int.printlnSomeInt() = println(this) //print some int
fun String.prinlnSomeString() = println(this) //print som String

data class Person(var name: String, var age: Int){
    fun showMyPerson () = println(this.toString())
}

