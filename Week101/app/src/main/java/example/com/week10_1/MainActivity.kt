package example.com.week10_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import example.com.week10_1.databinding.ActivityMainAlterBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainAlterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAlterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}