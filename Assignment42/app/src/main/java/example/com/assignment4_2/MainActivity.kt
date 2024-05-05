package example.com.assignment4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.TimePicker
import example.com.assignment4_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val DEBUG_TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 타이머 관련 binding
        val timer: Chronometer = binding.chrono

        // 날짜, 시간 관련 binding
        val setDate: RadioButton = binding.rdoCal
        val setTime: RadioButton = binding.rdoTime
        val radioGroup: RadioGroup = binding.radioGroup
        val calendarView: CalendarView = binding.calenderView
        val timePicker: TimePicker = binding.timerPicker
        val dateGroup: FrameLayout = binding.dateViews
        val textBar: TextView = binding.res

        var flag = false
        var time: Long = 0

        timer.setOnClickListener {
            if (!flag) { // 타이머가 비활성화 상태
                timer.base = SystemClock.elapsedRealtime() - time
                timer.start() // 타이머 시작
                flag = true

                radioGroup.visibility = View.VISIBLE
                dateGroup.visibility = View.VISIBLE

                Log.d(DEBUG_TAG, "Timer.base: ${timer.base}")
                Log.d(DEBUG_TAG, "SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}")
                Log.d(DEBUG_TAG, "Time: $time")
            }
        }

        var selectedYear = 0
        var selectedMonth = 0
        var selectedDay = 0

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month + 1 // 월은 0부터 시작하므로 +1
            selectedDay = dayOfMonth
        }

        textBar.setOnLongClickListener {
            if(flag) {
                timer.stop() // 타이머 정지
                flag = false
                time = SystemClock.elapsedRealtime() - timer.base

                radioGroup.visibility = View.INVISIBLE
                dateGroup.visibility = View.INVISIBLE

                // 선택한 시간 가져오기
                val hourOfDay = timePicker.hour
                val minute = timePicker.minute

                // TextView에 날짜 및 시간 설정
                textBar.text = String.format("%d년 %d월 %d일 %d시 %d분", selectedYear, selectedMonth, selectedDay, hourOfDay, minute)

                Log.d(DEBUG_TAG, "Time: $time")
                Log.d(DEBUG_TAG, "SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}")
                Log.d(DEBUG_TAG, "Timer.base: ${timer.base}")
            }
            true
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
}