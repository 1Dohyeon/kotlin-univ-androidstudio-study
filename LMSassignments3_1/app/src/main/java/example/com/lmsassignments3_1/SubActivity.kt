package example.com.lmsassignments3_1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import example.com.lmsassignments3_1.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (intent.getIntExtra("num", 0).toString() + " / " +
                intent.getStringExtra("next")+"/"+intent.data).also { binding.txtView1.text = it }
        binding.txtView2.text = intent.getStringExtra("edit")

        binding.btn1.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("res", "이것")
            }
            setResult(RESULT_OK, returnIntent)
            finish()
        }
        binding.btn2.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("res", "저것")
            }
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}