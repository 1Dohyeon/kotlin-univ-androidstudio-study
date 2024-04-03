package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Chronometer
import android.widget.MultiAutoCompleteTextView
import example.univ.myapplication.databinding.ActivityMainBinding
import example.univ.myapplication.databinding.AutocompleteBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val DEBUG_TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timer: Chronometer = binding.timer
        val startButton: Button = binding.start
        val stopButton: Button = binding.stop
        val labButton: Button = binding.lab
        val resetButton: Button = binding.reset

        // Start 버튼 클릭 리스너 설정
        startButton.setOnClickListener {
            if (!timer.isActivated) { // 타이머가 활성화되어 있지 않으면
                timer.start() // 타이머 시작
                startButton.visibility = View.INVISIBLE // Start 버튼 숨기기
                stopButton.visibility = View.VISIBLE // Stop 버튼 보이기
                labButton.visibility = View.VISIBLE // Lab 버튼 보이기
                resetButton.visibility = View.INVISIBLE // Reset 버튼 숨기기
                Log.d(DEBUG_TAG, "Timer started")
            }
        }

        // Stop 버튼 클릭 리스너 설정
        stopButton.setOnClickListener {
            if (timer.isActivated) { // 타이머가 활성화되어 있으면
                timer.stop() // 타이머 정지
                startButton.visibility = View.VISIBLE // Start 버튼 보이기
                stopButton.visibility = View.INVISIBLE // Stop 버튼 숨기기
                resetButton.visibility = View.VISIBLE // Reset 버튼 보이기
                labButton.visibility = View.INVISIBLE // Lab 버튼 숨기기
                Log.d(DEBUG_TAG, "Timer stopped")
            }
        }

        // Lab 버튼 클릭 리스너 설정
        labButton.setOnClickListener {
            // Lab 버튼 클릭 시 현재 시간을 Log에 출력
            Log.d(DEBUG_TAG, "Lab clicked")
        }

        // Reset 버튼 클릭 리스너 설정
        resetButton.setOnClickListener {
            // Reset 버튼 클릭 시 타이머를 초기화하고, 모든 버튼을 초기 상태로 되돌림
            timer.base = System.currentTimeMillis()
            timer.stop()
            startButton.visibility = View.VISIBLE // Start 버튼 보이기
            stopButton.visibility = View.INVISIBLE // Stop 버튼 숨기기
            resetButton.visibility = View.VISIBLE // Reset 버튼 보이기
            labButton.visibility = View.INVISIBLE // Lab 버튼 숨기기
            Log.d(DEBUG_TAG, "Timer reset")
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var DEBUG_TAG: String = "MyActivity"
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(DEBUG_TAG, "Action was DOWN")
                true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(DEBUG_TAG, "Action was MOVE")
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d(DEBUG_TAG, "Action was UP")
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(DEBUG_TAG, "Action was CANCEL")
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d(DEBUG_TAG, "Movement occured outside bounds of current screen element")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}