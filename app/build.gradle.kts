plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("io.realm.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wayne.hotnews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wayne.hotnews"
        minSdk = 30
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

    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")

    implementation("androidx.activity:activity-ktx:1.8.1")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-adapters:1.15.0")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")

    implementation("io.realm.kotlin:library-base:1.11.0")
    implementation("io.realm.kotlin:library-sync:1.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")

    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    implementation("com.github.bumptech.glide:glide:4.16.0")
}