## Setting

### Verson

1.19

### Gradle And Theme

**gradle**
``` kts
android{
    // ...

    buildFeatures {
        viewBinding = true
    }
}
dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```
**theme.xml**
```
<item name="colorPrimary">#1A7E2B</item>
```

---
## Projects

### 1. Week8

날짜별로 일기를 쓸 수 있는 기능
- 데이터 파일 형태로 저장
- 오늘 날짜의 일기가 바로 화면에 나오지 않는 에러가 발생

### 2. Week101

앱 가로 세로별 화면 나누는 기능 공부

### 3. Week102
### 4. Assignment4

간단한 타이머 구현 

### 5. Assignment42

타이머와 날짜, 시간을 이용하여 예약에 걸리는 시간 측정

### 6. Week111

onDraw() 을 통해 선과 원을 그리는 기능을 공부

### 8. Week113

두 개의 액티비티 다루는 법 공부

