package com.example.test

import android.util.Log
import kotlinx.coroutines.*

class Coroutines {

    init {
        //test1()
        //test2()
        //test3()
        //test4()
        //test5()
        //test6()
        //test7()
        //test8()
        //test9()
    }

    suspend fun printlnDelayed(msg: String){
        delay(3000)
        Log.d("global","Msg: $msg")
    }

    suspend fun calculateHardThingsFromNetwork(num:Int): Int{
        delay(1000)
        return num * 10
    }

    //Running on main thread
    private fun test1(){
        Log.d("global","One")
        runBlocking {
            printlnDelayed("Two")
        }
        Log.d("global","Three")
        //One -> Two -> Three
    }

    //Running on main thread
    private fun test2() = runBlocking {
        Log.d("global","One")
        printlnDelayed("Two")
        Log.d("global","Three")
        //One -> Two -> Three //Nice looking than test1()
    }

    //Running on another thread but still blocking the main thread
    private fun test3(){
        runBlocking(Dispatchers.Default) {
            Log.d("global","One") //Runs on Default thread
            printlnDelayed("Two") //Runs on Default thread
        }
        Log.d("global","Three") //Runs on main thread
        //One -> Two -> Three
    }

    //Running on main thread without block the main thread
    private fun test4(){
        Log.d("global","One")
        GlobalScope.launch {
            printlnDelayed("Two") //this runs but program will end first and never show
        }
        Log.d("global","Three")
        //One -> Three -> Two? //like volley
    }

    //Running on main thread without block the main thread
    //Running a new coroutine without block current thread and wait for it
    private fun test5() = runBlocking{
        Log.d("global","One") //Main Thread
        val job = GlobalScope.launch {
            printlnDelayed("Two") //DefaultDispatcher Thread
        } //returns a job
        Log.d("global","Three") //Main Thread

        job.join() //wait for a complete job
        //One -> Three -> Two
    }

    //Running on coroutineScope from runBlocking instead of GlobalScope
    private fun test6() = runBlocking {
        Log.d("global","One -> Thread: ${Thread.currentThread().name}") //Main Thread
        //Running on this coroutineScoput instead of GlobalScope
        //Launch can runs in specific thread -> launch(Dispatchers.Default)
        this.launch {
            printlnDelayed("Two -> Thread: ${Thread.currentThread().name}") //Main Thread
        }
        Log.d("global","Three -> Thread: ${Thread.currentThread().name}") //Main Thread
        //It is not necessary implicit wait for "Two" because localScope (this CoroutineScope) wait for "Two" automatically
        //One -> Three -> Two
    }

    //Waiting for something that is not a job like a api call
    private fun test7() = runBlocking {
        val num1 = async { calculateHardThingsFromNetwork(10) }.await() //wait for delay of 1 second
        val num2 = async { calculateHardThingsFromNetwork(10) }.await() //wait for another delay of 1 second
        val num3 = async { calculateHardThingsFromNetwork(10) }.await() //wait for another delay of 1 second

        val sum = num1 + num2 + num3
        Log.d("global","Sum: $sum")
    }

    //faster way than test7()
    private fun test8() = runBlocking {
        val num1 = async { calculateHardThingsFromNetwork(10) }
        val num2 = async { calculateHardThingsFromNetwork(10) }
        val num3 = async { calculateHardThingsFromNetwork(10) }

        val sum = num1.await() + num2.await() + num3.await() //await until every job is done -> total 1 second of delay
        Log.d("global","Sum: $sum")
    }

    //Same as test7() but nice looking
    private fun test9() = runBlocking {
        val num1 = withContext(Dispatchers.Default) { calculateHardThingsFromNetwork(10) } //wait for delay of 1 second
        val num2 = withContext(Dispatchers.Default) { calculateHardThingsFromNetwork(10) } //wait for another delay of 1 second
        val num3 = withContext(Dispatchers.Default) { calculateHardThingsFromNetwork(10) } //wait for another delay of 1 second

        val sum = num1 + num2 + num3
        Log.d("global","Sum: $sum")
        //This way is useful when u don't need do some concurrently as test8() and nice looking than test7()
    }
}