package example.com.week8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import example.com.week8.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    fun readDiary(fName: String) : String?{
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = applicationContext
        val TAG = "Debug"

        var cal = Calendar.getInstance()
        var cy = cal.get(Calendar.YEAR)
        var cm = cal.get(Calendar.MONTH)
        var cd = cal.get(Calendar.DAY_OF_MONTH)
        lateinit var fName: String

        binding.datePciker.init(cy, cm, cd) {view, year, month, day ->

        }
        binding.smtBtn.setOnClickListener {
            openFileOutput(fName, Context.MODE_PRIVATE).use {

            }
        }
    }
}