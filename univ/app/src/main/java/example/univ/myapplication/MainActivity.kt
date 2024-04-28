package example.univ.myapplication

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: TabLayoutBinding
    private val DEBUG_TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var selectedYear = 0
        var selectedMonth = 0
        var selectedDay = 0

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month + 1 // 월은 0부터 시작하므로 +1
            selectedDay = dayOfMonth
        }

        textBar.setOnLongClickListener {

            AlertDialog.Builder(this.activity).run {
                setTitle("HELLO")
                setIcon(android.R.drawable.ic_dialog_dialer)
                setNegativeButton("cancel", null)
                setPositiveButton("accept") { _, _ ->
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
                }
                show()
            }

            true
        }
    }
}
