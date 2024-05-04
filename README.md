## Version

1.19

---
## Projects

### 1. Week8

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
