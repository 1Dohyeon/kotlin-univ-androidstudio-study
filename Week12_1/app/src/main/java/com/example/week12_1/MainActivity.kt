package com.example.week12_1

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.week12_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val dbHelper = MyDatabase.MyDbHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = dbHelper.writableDatabase
        Log.d("MainActivity", "Start")
        val entryArr = mutableListOf(
            MyElement(1, "aespa", "Spicy", "MY WORLD", 68136),
            MyElement(2, "IVE", "I AM", "I've IVE", 153643),
            MyElement(3, "LESSERAFIM", "UNFORGIVEN", "UNFORGIVEN", 85725)
        )
        for (entry in entryArr) {
            val myentry = MyDatabase.MyDBContract.MyEntry
            val values = ContentValues().apply {
                put(myentry.rank, entry.rank)
                put(myentry.title, entry.title)
                put(myentry.artist, entry.artist)
                put(myentry.album, entry.album)
                put(myentry.num_like, entry.num_like)
            }
            val newRowId=db?.insert(myentry.TABLE_NAME, null, values)
            Log.d("MainActivity", "${newRowId.toString()}: ${values.toString()}")
        }
        db.close()
    }
}