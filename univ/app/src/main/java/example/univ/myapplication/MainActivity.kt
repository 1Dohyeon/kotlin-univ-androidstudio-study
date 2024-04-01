package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import example.univ.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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
    }
}