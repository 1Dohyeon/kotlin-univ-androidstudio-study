package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var nameBtn: Button
    lateinit var myIdBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameBtn = findViewById<Button> (R.id.nameBtn)
        nameBtn.setOnClickListener {
            Toast.makeText(applicationContext, "NAME",
                Toast.LENGTH_SHORT).show()
        }

        myIdBtn = findViewById<Button> (R.id.myIdBtn)
        myIdBtn.setOnClickListener {
            Toast.makeText(applicationContext, "ID",
                Toast.LENGTH_SHORT).show()
        }
    }
}