package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import example.univ.myapplication.databinding.ActivityMainBinding
import example.univ.myapplication.databinding.AutocompleteBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
     lateinit var binding2: AutocompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val DEBUG_TAG = "MyActivity"

        binding.timer.stop()

        // start 를 안 눌러도 timer가 흐르긴 함. start는 보여주기만 할 뿐
        binding.start.setOnClickListener {
            binding.timer.start()
            Log.d(DEBUG_TAG, "clicked_start")
        }

        // stop 도 마찬가지임. timer를 멈추지 않고, 보여주는 것만 멈춤
        binding.stop.setOnClickListener {
            binding.timer.stop()
            Log.d(DEBUG_TAG, "clicked_stop")
        }

//        autocomplete
//        binding2 = AutocompleteBinding.inflate(layoutInflater)
//        setContentView(binding2.root)
//        val countries = resources.getStringArray(R.array.countries_array)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countries)
//        binding2.auto.setAdapter(adapter)
//        binding2.multiauto.threshold = 1
//        binding2.multiauto.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var DEBUG_TAG: String = "MyActivity"
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