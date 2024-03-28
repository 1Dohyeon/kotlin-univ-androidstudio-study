package example.univ.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import example.univ.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var num1: Int
        var num2: Int
        var result: Int
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.B0.setOnClickListener {
            onClick(binding.B0)
        }

        binding.B1.setOnClickListener {
            onClick(binding.B1)
        }

        binding.B2.setOnClickListener {
            onClick(binding.B2)
        }

        binding.B3.setOnClickListener {
            onClick(binding.B3)
        }

        binding.B4.setOnClickListener {
            onClick(binding.B4)
        }

        binding.B5.setOnClickListener {
            onClick(binding.B5)
        }

        binding.B6.setOnClickListener {
            onClick(binding.B6)
        }

        binding.B7.setOnClickListener {
            onClick(binding.B7)
        }

        binding.B8.setOnClickListener {
            onClick(binding.B8)
        }

        binding.B9.setOnClickListener {
            onClick(binding.B9)
        }

        binding.add.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toInt()
                num2 = binding.num2.text.toString().toInt()

                result = num1 + num2
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.sub.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toInt()
                num2 = binding.num2.text.toString().toInt()

                result = num1 - num2
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.mul.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toInt()
                num2 = binding.num2.text.toString().toInt()

                result = num1 * num2
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.div.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toInt()
                num2 = binding.num2.text.toString().toInt()

                if (num2 != 0) {
                    result = num1 / num2
                    binding.result.text = getString(R.string.result, result.toString())
                } else {
                    Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun onClick(v: View?) {
        var input:String = (v as Button).text.toString()
        if(binding.num1.isFocused) {
            input = binding.num1.text.toString() + input
            binding.num1.setText(input)
        } else if(binding.num2.isFocused) {
            input = binding.num2.text.toString() + input
            binding.num2.setText(input)
        } else {
            Toast.makeText(applicationContext, "Please click num1 or num2", Toast.LENGTH_SHORT).show()
        }
    }

    // num1, num2 관련 예외 처리
    private fun validateNumbers(): Boolean {
        val num1Str = binding.num1.text.toString()
        val num2Str = binding.num2.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }

        // int 정수 범위 에러처리
        try {
            num1Str.toInt()
            num2Str.toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "가능한 정수 범위가 아닙니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}