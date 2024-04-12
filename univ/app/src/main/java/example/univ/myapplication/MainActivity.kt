package example.univ.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.RadioButton
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import example.univ.myapplication.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val DEBUG_TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 타이머 관련 binding
        val timer: Chronometer = binding.chrono
        val startBookingBtn: Button = binding.btnStart
        val endBookingBtn: Button = binding.btnEnd

        // 날짜, 시간 관련 binding
        val setDate: RadioButton = binding.rdoCal
        val setTime: RadioButton = binding.rdoTime
        val calendarView: CalendarView = binding.calenderView
        val timePicker: TimePicker = binding.timerPicker

        var flag = false
        var time: Long = SystemClock.elapsedRealtime() - timer.base

        // 예약 시작 버튼 이벤트
        startBookingBtn.setOnClickListener {
            if (!flag) { // 타이머가 비활성화 상태
                timer.base = SystemClock.elapsedRealtime() - time
                timer.start() // 타이머 시작
                flag = true

                Log.d(DEBUG_TAG, "Timer.base: ${timer.base}")
                Log.d(DEBUG_TAG, "SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}")
                Log.d(DEBUG_TAG, "Time: $time")
            }
        }

        val calenderView = findViewById<CalendarView>(R.id.calenderView)
        val timerPicker = findViewById<TimePicker>(R.id.timerPicker)
        val resTextView = findViewById<TextView>(R.id.res)

        // 예약 완료 버튼 이벤트
        endBookingBtn.setOnClickListener {
            if (flag) { // 타이머가 활성화 상태
                timer.stop() // 타이머 정지
                flag = false
                time = SystemClock.elapsedRealtime() - timer.base

                // 선택한 날짜 가져오기
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = calenderView.date
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1 // 월은 0부터 시작하므로 +1
                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

                // 선택한 시간 가져오기
                val hourOfDay = timerPicker.hour
                val minute = timerPicker.minute

                // TextView에 날짜 및 시간 설정
                resTextView.text = String.format("%d년 %d월 %d일 %d시 %d분", year, month, dayOfMonth, hourOfDay, minute)

                Log.d(DEBUG_TAG, "Time: $time")
                Log.d(DEBUG_TAG, "SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}")
                Log.d(DEBUG_TAG, "Timer.base: ${timer.base}")
            }
        }

        // RadioGroup 이벤트
        setDate.setOnClickListener {
            calendarView.visibility = View.VISIBLE
            timePicker.visibility = View.INVISIBLE
        }

        setTime.setOnClickListener {
            timePicker.visibility = View.VISIBLE
            calendarView.visibility = View.INVISIBLE
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val DEBUG_TAG: String = "MyActivity"
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