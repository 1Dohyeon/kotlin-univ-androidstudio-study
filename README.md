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
    <style name="Base.Theme.LMSassignments3_1" parent="Theme.Material3.DayNight">
```

---
## Projects

### 1. LMSassignments3_1

두 개의 액티비티를 다루는 연습
- Log를 통해 lifecycle에 대하여 공부

### 2. LMSassignments3_2
