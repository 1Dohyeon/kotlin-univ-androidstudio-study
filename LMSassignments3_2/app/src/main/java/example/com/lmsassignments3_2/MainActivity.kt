package example.com.lmsassignments3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import example.com.lmsassignments3_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private var count: Int = 0

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart $count")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume $count")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause $count")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop $count")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy $count")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate $count")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** savedInstanceState 를 이용하여 화면 회전 전 상태를 불러옴 */
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0)
        }
        binding.countTxt.text = "Count: $count"

        // Count 버튼 클릭 이벤트 처리
        binding.countBtn.setOnClickListener {
            count++
            binding.countTxt.text = "Count: $count"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstance $count")
        // 상태 저장
        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstance $count")
        count = savedInstanceState.getInt("count", 0)
        Log.d(TAG, "onRestoreInstanceLoad $count")
    }
}