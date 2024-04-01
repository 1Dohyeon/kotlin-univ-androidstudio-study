package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import example.univ.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.start.setOnClickListener { binding.timer.start() }
        binding.stop.setOnClickListener { binding.timer.stop() }
    }
}