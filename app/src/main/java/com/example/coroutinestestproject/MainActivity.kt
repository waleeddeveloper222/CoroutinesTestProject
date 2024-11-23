package com.example.coroutinestestproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        GlobalScope.launch(Dispatchers.Main) {
            Log.i("MainCoroutines", "GlobalScope is Working")
            Log.i("MainCoroutines", "GlobalScope ${Thread.currentThread().name}")

            withContext(Dispatchers.IO) {

                val test = testFun()

                withContext(Dispatchers.Default) {

                    Log.i("MainCoroutines", test)
                }
            }


        }
    }


    private suspend fun testFun(): String {
        delay(4000L)
        return "test function with delay 4 seconds"

    }
}