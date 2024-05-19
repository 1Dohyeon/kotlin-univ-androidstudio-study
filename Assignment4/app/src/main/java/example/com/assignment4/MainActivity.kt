package example.com.assignment4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.annotation.RequiresApi
import example.com.assignment4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val DEBUG_TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timer: Chronometer = binding.timer
        val startButton: Button = binding.start
        val stopButton: Button = binding.stop
        val resetButton: Button = binding.reset

        var flag: Boolean = false
        var time: Long = 0

        // Start 버튼 클릭 리스너 설정
        startButton.setOnClickListener {
            if (!flag) { // 타이머가 활성화되어 있지 않으면
                timer.base = SystemClock.elapsedRealtime() - time

                timer.start() // 타이머 시작
                flag = true

                startButton.visibility = View.INVISIBLE // Start 버튼 숨기기
                stopButton.visibility = View.VISIBLE // Stop 버튼 보이기

                Log.d(DEBUG_TAG, "Timer started")
                Log.d("DEBUG", "timer.base: ${timer.base}, SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}, time: $time")
            }
        }

        // Stop 버튼 클릭 리스너 설정
        stopButton.setOnClickListener {
            if (flag) { // 타이머가 활성화되어 있으면
                timer.stop() // 타이머 정지
                flag = false
                time = SystemClock.elapsedRealtime() - timer.base

                startButton.visibility = View.VISIBLE // Start 버튼 보이기
                stopButton.visibility = View.INVISIBLE // Stop 버튼 숨기기

                Log.d(DEBUG_TAG, "Timer stopped")
                Log.d("DEBUG", "timer.base: ${timer.base}, SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}, time: $time")
            }
        }


        // Reset 버튼 클릭 리스너 설정
        resetButton.setOnClickListener {
            stopButton.performClick()

            timer.base = SystemClock.elapsedRealtime()
            time = 0

            Log.d(DEBUG_TAG, "Timer reset")
            Log.d("DEBUG", "timer.base: ${timer.base}, SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}, time: $time")
        }
    }

}