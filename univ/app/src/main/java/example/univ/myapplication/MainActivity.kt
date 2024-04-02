package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import example.univ.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var num1: Double
        var num2: Double
        var result: Double
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ---- 연산 버튼 ----

        binding.add.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toDouble()
                num2 = binding.num2.text.toString().toDouble()

                result = num1 + num2
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.sub.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toDouble()
                num2 = binding.num2.text.toString().toDouble()

                result = num1 - num2
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.mul.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toDouble()
                num2 = binding.num2.text.toString().toDouble()

                result = num1 * num2
                binding.result.text = getString(R.string.result, result.toString())
            }
        }

        binding.div.setOnClickListener {
            if (validateNumbers()) {
                num1 = binding.num1.text.toString().toDouble()
                num2 = binding.num2.text.toString().toDouble()

                if (num2 != 0.0) {
                    result = num1 / num2
                    binding.result.text = getString(R.string.result, result.toString())
                } else {
                    Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.clear.setOnClickListener {
            if (validateNumbers()) {
                num1 = 0.0
                num2 = 0.0
                result = 0.0

                binding.num1.setText("")
                binding.num2.setText("")
                binding.result.setText("")
            }
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

        // int 범위 밖을 넘어갔을 때 에러 처리 및 정수가 아닌 다른 문자를 받았을 때 에러처리
        try {
            num1Str.toDouble()
            num2Str.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "가능한 정수 범위가 아닙니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}