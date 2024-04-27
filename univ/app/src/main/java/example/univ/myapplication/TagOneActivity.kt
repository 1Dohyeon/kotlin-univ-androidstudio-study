package example.univ.myapplication

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import example.univ.myapplication.databinding.TagOnePageBinding

class  TagOneActivity: Fragment() {
    private lateinit var binding: TagOnePageBinding
    private val DEBUG_TAG = "TagOnePage"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = TagOnePageBinding.inflate(layoutInflater)

        val timer: Chronometer = binding.timer
        val startButton: Button = binding.start
        val stopButton: Button = binding.stop
        val resetButton: Button = binding.reset

        var flag: Boolean = false
        var time: Long = SystemClock.elapsedRealtime() - timer.base

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
            // Reset 버튼 클릭 시 타이머를 초기화하고, 모든 버튼을 초기 상태로 되돌림
            timer.base = SystemClock.elapsedRealtime()
            stopButton.performClick()
            time = SystemClock.elapsedRealtime() - timer.base

            /** xml 접근 방식 */
            startButton.visibility = View.VISIBLE // Start 버튼 보이기
            stopButton.visibility = View.INVISIBLE // Stop 버튼 숨기기

            Log.d(DEBUG_TAG, "Timer reset")
            Log.d(DEBUG_TAG, "timer.base: ${timer.base}, SystemClock.elapsedRealtime(): ${SystemClock.elapsedRealtime()}, time: $time")
        }

        return binding.root
    }
}