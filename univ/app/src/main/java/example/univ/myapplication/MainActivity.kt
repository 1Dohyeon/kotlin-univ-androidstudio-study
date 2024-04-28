package example.univ.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: TabLayoutBinding
    private val DEBUG_TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = TabLayoutBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val DEBUG_TAG: String = "MyActivity"
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(DEBUG_TAG, "Action was DOWN")
                true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(DEBUG_TAG, "Action was MOVE")
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d(DEBUG_TAG, "Action was UP")
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(DEBUG_TAG, "Action was CANCEL")
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d(DEBUG_TAG, "Movement occured outside bounds of current screen element")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}