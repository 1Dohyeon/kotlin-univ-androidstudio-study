package example.univ.midterm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import example.univ.midterm1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startBtn = binding.startBtn
        val stopBtn = binding.stopBtn
        val resetBtn = binding.resetBtn
        val labBtn = binding.labBtn

        val bigTime = binding.bigTime
        var timeB: Long = 0
        var flagB: Boolean = false

        val smallTime = binding.smallTime
        var timeS: Long = 0
        var flagS: Boolean = false

        startBtn.setOnClickListener {
            if(!flagB && !flagS) {
                bigTime.start()
                bigTime.base = SystemClock.elapsedRealtime() - timeB

                smallTime.start()
                smallTime.base = SystemClock.elapsedRealtime() - timeS

                flagS = true
                flagB = true

                startBtn.visibility = View.INVISIBLE
                stopBtn.visibility = View.VISIBLE
                resetBtn.visibility = View.INVISIBLE
                labBtn.visibility = View.VISIBLE
            }
        }

        stopBtn.setOnClickListener {
            if(flagB && flagS) {
                smallTime.stop()
                timeS = SystemClock.elapsedRealtime() - smallTime.base

                bigTime.stop()
                timeB = SystemClock.elapsedRealtime() - bigTime.base

                flagS = false
                flagB = false

                startBtn.visibility = View.VISIBLE
                stopBtn.visibility = View.INVISIBLE
                resetBtn.visibility = View.VISIBLE
                labBtn.visibility = View.INVISIBLE
            }
        }

        resetBtn.setOnClickListener {
            if(!flagB && !flagS) {
                smallTime.base = SystemClock.elapsedRealtime()
                bigTime.base = SystemClock.elapsedRealtime()
                timeS = 0
                timeB = 0
            }
        }

        labBtn.setOnClickListener {
            if(flagB && flagS) {
                smallTime.base = SystemClock.elapsedRealtime()
                timeS = 0
            }
        }

    }
}