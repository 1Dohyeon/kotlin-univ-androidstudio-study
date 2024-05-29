package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        var num1: Double
        var num2: Double
        var result: Double
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // number 버튼 입력 처리
        setOnClickNumberButtons()

        // ---- 연산 버튼 ----
        fun performCalculation(operator: Char) {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toDouble()
                num2 = binding.num2.text.toString().toDouble()

                result =
                    when (operator) {
                        '+' -> num1 + num2
                        '-' -> num1 - num2
                        '*' -> num1 * num2
                        '/' -> if (num2 != 0.0) {
                            num1 / num2
                        } else {
                            Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                            return
                        }
                        else -> {
                            // 다른 연산자일 때 예외 처리, 직접 연산자 넣어줄거라 주석처리
                            // Toast.makeText(this, "잘못된 연산자입니다.", Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.add.setOnClickListener {
            performCalculation('+')
        }

        binding.sub.setOnClickListener {
            performCalculation('-')
        }

        binding.mul.setOnClickListener {
            performCalculation('*')
        }

        binding.div.setOnClickListener {
            performCalculation('/')
        }


        binding.clear.setOnClickListener {
            binding.num1.setText("")
            binding.num2.setText("")
            binding.result.setText("Result: ")
        }
    }



    /** 터치 이벤트를 처리하고 이벤트가 발생한 경우 현재 포커스된 뷰가 있으면 키보드를 숨김.
     * 이벤트가 계속해서 처리될 수 있도록 super.dispatchTouchEvent(ev)를 호출 */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    /** point & number 버튼 클릭 시에 onClick 메서드 실행 */
    private fun setOnClickNumberButtons() {
        val numberButtons = listOf(binding.B0, binding.B1, binding.B2,
            binding.B3, binding.B4, binding.B5, binding.B6,
            binding.B7, binding.B8, binding.B9, binding.point)

        numberButtons.forEach {
                btn -> btn.setOnClickListener { onClick(btn) }
        }
    }

    /** View 객체에서 클릭된 버튼의 텍스트를 가져와서 문자열로 변환한 후,
     * 현재 포커스된 EditText에 해당 문자열을 추가 */
    private fun onClick(v: View?) {
        // 버튼의 value 를 문자열로 가져옴
        var input:String = (v as Button).text.toString()

        // 포커싱된 View에 기존 문자열과 현재 클릭한 번호의 문자열을 추가해주고 업데이트 함
        if(binding.num1.isFocused) {
            input = binding.num1.text.toString() + input
            binding.num1.setText(input)
        } else if(binding.num2.isFocused) {
            input = binding.num2.text.toString() + input
            binding.num2.setText(input)
        } else {
            // num1, num2 View 를 클릭하지 않고, 번호 버튼을 클릭했을 경우
            Toast.makeText(applicationContext, "num1 또는 num2 을 클릭해야 합니다.",
                Toast.LENGTH_SHORT).show()
        }
    }

    /** num1, num2 관련 예외 처리 */
    private fun validateNumbers(): Boolean {
        val num1Str = binding.num1.text.toString()
        val num2Str = binding.num2.text.toString()

        // num1, num2 가 비었는데 연산을 실행할 경우
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }

        // 소수 아닌 다른 문자를 받았을 때 에러 처리
        try {
            num1Str.toDouble()
            num2Str.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "가능한 범위가 아닙니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}