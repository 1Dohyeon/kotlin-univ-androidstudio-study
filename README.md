### Version

1.19

### Branch

**1. main** = univ#3

**2. univ**
  - #1 : 2-1 "기본적인 폴더 구조와 `Toast`로 메시지 띄우는 방법"
    
  - #2 : 3-2 "`binding` 사용 방법과 `EditText`에 적힌 text를 추출하는 방법(`binding.num1.text.toString().toInt()`)을 이용하여 간단한 계산기 구현"
 
  - #3 : 4-1, 4-2 "#2 에서 만든 계산기에 `EditText`로 숫자를 받는 것이 아닌 숫자 버튼을 통해서 숫자를 받게 함. 숫자 버튼을 이용하고, 버튼에 적힌 숫자를 추출하여 `focused` 된 `EditText`에 `setText()`함(`binding.num1.isFocused`, `binding.num1.setText(input)`)"
    
  - #4 : calculator(a2) "#2 에서 만든 계산기에 예외처리를 추가함. `Double`타입을 통해서 소수점 연산이 가능하게 함"
    
  - #5 : 5-1, 5-2 "xml 에서 `Chronometer`를 이용하여 타이머 뷰를 생성하고, 이를 바인딩 하여 timer 기능을 구현. timer를 start, stop, lab(최대 5개 기록들 저장), +5초, -5초 기능을 구현. 또한 버튼은 최대 4개로 timer 작동 상태에 따라 보여주는 버튼이 다름. `CircularQueue` 자료구조를 이용하여 기록 저장 및 삭제"

  - #6 : 6-1, 6-2 " 예약 프로그램으로 `Chronometer`를 이용하여 예약에 걸린 시간을 구하 `RadioButton`을 통해서 날짜와 시간을 선택하게 함. 날짜를 선택하기 위한 `CalendarView`에서 값을 get하여 불러오고, 시간을 선택하기 위한 `TimePicker`의 값을 hour, minute으로 나누어 불러옴. 값을 불러오기 위해 `findViewById`이용" 

  - #7 : caculator(a3) "#3에서 디자인한 숫자 버튼이 있는 계산기와 #4에서 에러처리를 한 계산기를 합쳐서 숫자버튼이 있고, 소수점 계산이 가능하고, 여러 상황에서의 예외 처리를 한 계산기 구현"
 
  -  #8 : 하노이 알고리즘, 7-1 "`themes.xml`에서 기본 색상 변경 후 Toolbar 제작 및 menu layout Toolbar에 설정, `Fragment`를 통해 두 화면을 버튼으로 넘기면서 보여줌"

  -  #9 : 7-2 "TabLayout 기본, adapter와 `viewpager`를 사용한 tablayout 응용"
 
  -  #10: timer & 예약프로그램(tab layouy을 통해 나누어 구현)
 
  -  #11: hanoi 프로그램
 
  -  #12: (a4) #10과 동일하지만 project를 나눔

**3. univ2**
univ처럼 브랜치로 분리 X -> project로 분리 
  - week8
