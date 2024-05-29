package com.example.week12_1

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week12_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val dbHelper = MyDatabase.MyDbHelper(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Adapter with empty list
        adapter = MyAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter

        // Set onClickListener for reset button
        binding.resetButton.setOnClickListener {
            resetDatabase()
        }

        // Set onClickListener for submit button
        binding.submitBtn.setOnClickListener {
            addEntry()
        }

        // Load initial data
        loadData()
    }

    private fun resetDatabase() {
        val db = dbHelper.writableDatabase
        dbHelper.onUpgrade(db, 1, 1)
        db.close()

        // Re-populate database
        populateDatabase()
        loadData()
    }

    private fun populateDatabase() {
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
            try {
                val newRowId = db.insertOrThrow(myentry.TABLE_NAME, null, values)
                Log.d("MainActivity", newRowId.toString())
            } catch (e: SQLiteConstraintException) {
                db?.update(myentry.TABLE_NAME, values, "${myentry.title} LIKE ?", arrayOf(entry.title))
            }
        }
        db.close()
    }

    private fun addEntry() {
        val rank = binding.rankingEditText.text.toString().toIntOrNull() ?: return
        val artist = binding.singerEditText.text.toString()
        val title = binding.titleEditText.text.toString()
        val album = binding.albumEditText.text.toString()
        val numLike = binding.likeEditText.text.toString().toIntOrNull() ?: return

        val entry = MyElement(rank, artist, title, album, numLike)

        val db = dbHelper.writableDatabase
        val myentry = MyDatabase.MyDBContract.MyEntry
        val values = ContentValues().apply {
            put(myentry.rank, entry.rank)
            put(myentry.title, entry.title)
            put(myentry.artist, entry.artist)
            put(myentry.album, entry.album)
            put(myentry.num_like, entry.num_like)
        }
        try {
            val newRowId = db.insertOrThrow(myentry.TABLE_NAME, null, values)
            Log.d("MainActivity", newRowId.toString())
        } catch (e: SQLiteConstraintException) {
            db.update(myentry.TABLE_NAME, values, "${myentry.title} LIKE ?", arrayOf(entry.title))
        }
        db.close()

        loadData()
    }

    private fun loadData() {
        val db = dbHelper.readableDatabase
        val data = dbHelper.selectAll()
        db.close()

        adapter.setList(data)
    }
}
