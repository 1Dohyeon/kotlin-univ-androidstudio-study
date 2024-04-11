package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import example.univ.myapplication.databinding.ActivityMainBinding
import java.lang.reflect.Array
import java.util.Arrays

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
        val plus5: Button = binding.plus5
        val sub5: Button = binding.sub5

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
                labButton.visibility = View.VISIBLE // Lab 버튼 보이기
                resetButton.visibility = View.INVISIBLE // Reset 버튼 숨기기

                Log.d(DEBUG_TAG, "Timer started")
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
                resetButton.visibility = View.VISIBLE // Reset 버튼 보이기
                labButton.visibility = View.INVISIBLE // Lab 버튼 숨기기

                Log.d(DEBUG_TAG, "Timer stopped")
            }
        }

        val maxSize = 5 // 큐의 최대 크기 설정
        val circularQueue = CircularQueue(maxSize)

        // 큐의 원소들을 times LinearLayout에 보여주는 함수
        fun displayQueueElements(queue: LongArray) {
            val timesLayout = findViewById<LinearLayout>(R.id.times)
            timesLayout.removeAllViews() // 이전에 추가된 뷰들을 모두 제거

            // 큐의 모든 원소를 반복하면서 TextView로 만들어 timesLayout에 추가
            queue.forEachIndexed { index, element ->
                val textView = TextView(this)
                textView.text = "Element $index: $element" // 각 원소의 값을 보여줌
                timesLayout.addView(textView) // timesLayout에 TextView 추가
            }
        }

        // Lab 버튼 클릭 리스너 설정
        labButton.setOnClickListener {
            // 현재 시간을 milliseconds로 가져옴
            val currentTime = System.currentTimeMillis()

            // 큐에 현재 시간 추가
            circularQueue.enqueue(currentTime)

            // Log에 현재 큐의 값을 출력
            val currentQueue = circularQueue.toArray()
            Log.d(DEBUG_TAG, "Lab clicked. Current queue: ${currentQueue.contentToString()}")

            // UI 업데이트: times LinearLayout에 큐의 원소들을 보여줌
            displayQueueElements(currentQueue)
        }



        // Reset 버튼 클릭 리스너 설정
        resetButton.setOnClickListener {
            // Reset 버튼 클릭 시 타이머를 초기화하고, 모든 버튼을 초기 상태로 되돌림
            timer.base = SystemClock.elapsedRealtime()
            time = SystemClock.elapsedRealtime() - timer.base

            startButton.visibility = View.VISIBLE // Start 버튼 보이기
            stopButton.visibility = View.INVISIBLE // Stop 버튼 숨기기
            resetButton.visibility = View.VISIBLE // Reset 버튼 보이기
            labButton.visibility = View.INVISIBLE // Lab 버튼 숨기기
            Log.d(DEBUG_TAG, "Timer reset")
        }

        // +5 버튼
        plus5.setOnClickListener {
            if(!flag) { // 타이머가 활성화되어 있지 않으면
                time += 5000 // 5초를 밀리초로 변환하여 추가
                timer.base = SystemClock.elapsedRealtime() + time // 기준 시간 업데이트
            }
        }

        // -5 버튼
        sub5.setOnClickListener {
            if(!flag) { // 타이머가 활성화되어 있지 않으면
                time -= 5000 // 5초를 밀리초로 변환하여 감소
                if (time < 0) time = 0 // 음수 방지
                timer.base = SystemClock.elapsedRealtime() + time // 기준 시간 업데이트
            }
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

class CircularQueue(private val maxSize: Int) {
    private val array = LongArray(maxSize)
    private var front = -1
    private var rear = -1
    private var size = 0

    fun enqueue(value: Long) {
        if (isFull()) {
            dequeue() // 큐가 꽉 차있다면 첫 번째 요소를 제거하여 공간을 확보
        }

        if (isEmpty()) {
            front = 0
            rear = 0
        } else {
            rear = (rear + 1) % maxSize // rear를 다음 위치로 이동 (원형 구조)
        }

        array[rear] = value
        size++
    }

    fun dequeue(): Long {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }

        val dequeuedValue = array[front]
        if (front == rear) {
            // 큐에 남아 있는 요소가 마지막 하나라면 front와 rear를 초기화
            front = -1
            rear = -1
        } else {
            front = (front + 1) % maxSize // front를 다음 위치로 이동 (원형 구조)
        }

        size--
        return dequeuedValue
    }

    private fun isEmpty(): Boolean {
        return size == 0
    }

    private fun isFull(): Boolean {
        return size == maxSize
    }

    fun toArray(): LongArray {
        val result = LongArray(size)
        var index = 0
        var i = front
        while (index < size) {
            result[index] = array[i]
            i = (i + 1) % maxSize // 배열의 끝에 도달하면 처음으로 돌아감 (원형 구조)
            index++
        }
        return result
    }
}
