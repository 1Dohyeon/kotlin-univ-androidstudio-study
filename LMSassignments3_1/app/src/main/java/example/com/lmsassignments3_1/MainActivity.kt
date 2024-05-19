package example.com.lmsassignments3_1

import android.content.ActivityNotFoundException
import androidx.activity.result.ActivityResult
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import example.com.lmsassignments3_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reqLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
                result: ActivityResult ->
            if(result.resultCode == RESULT_OK)  {
                Log.d(TAG, "return")
                val intent = result.data
                val clickedBtn : String? = intent?.getStringExtra("res")
                "btn $clickedBtn".also { binding.txtMainView1.text = it }
            }
        }


        binding.btnSub.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:114")).apply {
                putExtra("next", "level")
            }
            intent.putExtra("num", 30)
            intent.putExtra("edit", binding.editTxt.text.toString())

            try {
                // startActivity(intent)
                reqLauncher.launch(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "No app for action", Toast.LENGTH_SHORT).show()
            }
        }
    }

}