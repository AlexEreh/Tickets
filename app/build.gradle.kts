plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.serialization)
}

android {
    namespace = "ru.alexereh.tickets"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.alexereh.tickets"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.datastorePreferences)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinxConverter)
    implementation(libs.kotlinx.serialization.json)

    // Dagger2/Hilt
    implementation(libs.dagger.hiltAndroid)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.dagger.hiltAndroidCompiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}