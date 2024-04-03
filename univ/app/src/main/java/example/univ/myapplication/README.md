**1. 주어진 ADD/SUB/MUL/DIV 연산 작동 하게 기본 구현**
```kotlin
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
```
EditText View(num1, num2) 에 적힌 text를 double 타입으로 변환하여 각 버튼에 맞게 연산한다.
div 연산에서 num2(분모)가 0일 경우 Toast를 이용하여 에러 처리를 해주었다.

**2, 3. 입력 값이 없거나 0으로 나눌 때의 예외처리 + 그 밖의 예외처리가 있을까? 있다면 추가하고 없으면 없다고 할 것**
```kotlin
/** num1, num2 관련 예외 처리 */
    private fun validateNumbers(): Boolean {
        val num1Str = binding.num1.text.toString()
        val num2Str = binding.num2.text.toString()

        // num1, num2 가 비었는데 연산을 실행할 경우
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }

        // double 범위 밖을 넘어갔을 때 에러 처리 및 정수가 아닌 다른 문자를 받았을 때 에러처리
        try {
            num1Str.toDouble()
            num2Str.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "가능한 정수 범위가 아닙니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
```
0으로 나눌 때의 에러처리는 div 버튼 이벤트에서 처리하였다.
위 코드에서는 EditText View(num1, num2) 에 작힌 text를 string 으로 가져온다.
그 후 비어있는 지 확인한다. 비어있다면 Toast를 이용하여 예외 처리를 해주었다.
그 후 연산을 위해 string을 다시 double로 변환해준다. 
이때 에러가 발생한다면 숫자가 아니거나, 범위를 벗어난 것이므로 Toast를 이용하여 예외 처리를 해준다.

**4. 소수점 연산이 가능하도록 코드 수정**
```kotlin
        var num1: Double
        var num2: Double
        var result: Double
```
Int 타입이었던 num1, num2, result 를 Double로 바꿈으로써 소수점 연산이 가능하게 되었다.
연산 버튼도 toDouble() 을 통해 형변환을 double로 바꿔주고 연산하였다.

**5. DIV아래에 입력 초기화 버튼 CLEAR 버튼을 만들고 버튼을 클릭하면 num1, num2,result 에 있는 숫자가 사라지고 초기화 되도록 설정**
```xml
    <Button
        android:id="@+id/clear"
        android:textSize="15sp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/clear" />
```

```kotlin
        binding.clear.setOnClickListener {
            binding.num1.setText("")
            binding.num2.setText("")
            binding.result.setText("")
        }
```
EditText View 의 text를 빈 문자열인 "" 로 초기화 시켰다.

**6. 이로 인해 생기는 예외가 존재할까? 있다면 추가하고 없으면 없다고 할 것**
없다
























