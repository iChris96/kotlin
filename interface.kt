package bosch.com.boschdaytest.activity

interface Named {
    val name: String //abstract
}

interface Person : Named {
    val firstName: String //abstract
    val lastName: String //abstract

    override val name: String get() = "-> $firstName $lastName"
}

data class Employee(
        // implementing 'name' is not required
        override val firstName: String,
        override val lastName: String
) : Person

class Main{
   
   var someone = Employee("Someone", "Walshy")
   Log.d("global", "${someone.name}") //-> Someone Walshy
   Log.d("global", "${someone.firstName}") //Someone
}