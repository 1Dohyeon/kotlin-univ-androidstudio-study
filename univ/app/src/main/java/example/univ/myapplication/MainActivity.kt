package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import example.univ.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Tool Bar 의 menu */
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_btn_colored_material)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        /** Fragment */
        binding.button1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragView.id, ExampleFragment())
                .commit()
            Log.d(TAG, "Button 1 clicked")
        }

        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragView.id, ExampleFragmentTwo())
                .commit()
            Log.d(TAG, "Button 2 clicked")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Log.d(TAG, "item1 clicked")
            }
            R.id.item2 -> {
                Log.d(TAG, "item2 clicked")
            }
            R.id.item3 -> {
                Log.d(TAG, "item3 clicked")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}