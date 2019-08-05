/* 1. Koin Init */
class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(listModule))
    }
}

/* 2. Classes */
data class AwesomeBean(var x:Int, var y:Int)
data class AwesomeFactory(var x:Int, var y:Int)

class Controller(private val mAwesome: Awesome) {

    fun printSomething(){
        Log.d("global","Contoller mAwesome: ${mAwesome.x}")
    }

    fun modifyAwesome(value: Int){
        mAwesome.x = value
    }
}

class Controller2(private val mAwesomeFactory: AwesomeFactory) {

    fun printSomething(){
        Log.d("global","Contoller2 mAwesomeFactory: ${mAwesomeFactory.x}")
    }

    fun modifyAwesome(value: Int){
        mAwesomeFactory.x = value
    }
}


/* 3. Module */
val listModule: Module = applicationContext {
    bean { AwesomeBean(5,5)} //single
    factory{ AwesomeFactory(9,9)} //Factory
    bean { Controller(get()) }
    bean { Controller2(get()) }
}

/* 4. Activity 1 */
class MainActivity : AppCompatActivity(), PostAdapterInterface { 

    //private val postsViewModel by viewModel<PostsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Log.d("global","##MainActivity onCreate##")

        val myAwesomeBean:AwesomeBean by inject() //Bean Awesome by inject
        Log.d("global", "myAwesomeBean: ${myAwesomeBean.x}") //prints -> 5

        myAwesomeBean.x = 7
        Log.d("global", "myAwesomeBean: ${myAwesomeBean.x}") //prints -> 7

        val myAwesome2 = Awesome(45,45) //New Awesome
        Log.d("global", "myAwesome2: ${myAwesome2.x}") //prints -> 45

        val myAwesomeFactory:AwesomeFactory by inject()//Factory Awesome by inject
        Log.d("global", "myAwesomeFactory: ${myAwesomeFactory.x}") //prints -> 9

        myAwesomeFactory.x = 100
        Log.d("global", "myAwesomeFactory: ${myAwesomeFactory.x}") //prints -> 100

        
        val controller: Controller by inject() //Bean Controller with Bean Awesome
        controller.printSomething() //prints -> 7
        controller.modifyAwesome(700)
        controller.printSomething() //prints -> 700

        val controller2: Controller2 by inject() //Bean Controller with Factory Awesome 
        controller2.printSomething() //prints -> 9
        controller.modifyAwesome(900)
        controller.printSomething() //prints -> 900
    }
}

/* 4. Activity 2 */
class CommentsActivity : AppCompatActivity() {

    //private val commentsViewModel by viewModel<CommentsViewModel>()
    //private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        //Log.d("global","##CommentsActivity onCreate##")

        val myAwesomeBean:AwesomeBean by inject()
        Log.d("global", "myAwesomeBean: ${myAwesomeBean.x}") //prints -> 700

        val myAwesomeFactory:AwesomeFactory by inject()
        Log.d("global", "myAwesomeFactory: ${myAwesomeFactory.x}") //prints -> 9

        val controller: Controller by inject() //Bean Controller wit Bean Awesome
        controller.printSomething() //prints -> 700

        val controller2: Controller2 by inject() //Bean Controller with Factory Awesome
        controller2.printSomething() //prints -> 900


    }
}