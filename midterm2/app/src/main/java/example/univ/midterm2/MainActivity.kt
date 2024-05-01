package example.univ.midterm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import example.univ.midterm2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}