### 4-1

1. **linear_layout.xml**
    - LinearLayout 사용
2. **relative_layout.xml**
    - RelativeLayout 사용
3. **frame_layout.xml** and **FrameActivity.kt**
    - LinearLayout 사용
      - 3개의 버튼을 수직 정렬함.
    - FrameLayout 사용
      - 이를 통해 3가지 색상의 TextView 를 겹치게 둠
    - FrameActivity 에서 위 3가지 버튼들을 클릭할 시에 지정된 색상의 TextView만 보여줌
      - 작동시키려면 FrameActivity 코드 MainActivity에 복붙하면 됨

### 4-2

1. **activity_main.xml**
   - LinearLayout 사용하여 계산기 ui 구현(숫자 버튼, 연산 버튼 등)
2. **MainActivity.kt**
   - activity_main 에 있는 버튼들 이벤트 처리