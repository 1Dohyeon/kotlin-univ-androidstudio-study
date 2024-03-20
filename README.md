1. View 이용
2. View Binding 을 통해서 간단한 계산기 앱 구현


**MainActivity**

MainActivity 코드 중복 처리를 아래와 같이 할 수 있다. 

``` kotlin
package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import example.univ.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener { performOperation { num1, num2 -> num1 + num2 } }
        binding.sub.setOnClickListener { performOperation { num1, num2 -> num1 - num2 } }
        binding.mul.setOnClickListener { performOperation { num1, num2 -> num1 * num2 } }
        binding.div.setOnClickListener {
            performOperation { num1, num2 ->
                if (num2 != 0) num1 / num2 else 0
            }
        }
    }

    private fun performOperation(operation: (Int, Int) -> Int) {
        val num1Str = binding.num1.text.toString()
        val num2Str = binding.num2.text.toString()

        if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
            val result = operation(num1Str.toInt(), num2Str.toInt())
            binding.result.text = getString(R.string.result, result.toString())
        } else {
            Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}

```
