package example.com.week11_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import example.com.week11_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var count = 0
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate $count")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         *  */
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart $count")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume $count")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause $count")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop $count")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy $count")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstance $count")
        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstance $count")
        count = savedInstanceState.getInt("count", 0)
        Log.d(TAG, "onRestoreInstanceLoad $count")
    }
}