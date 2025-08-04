import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"211ac8480e94ad451b4f949e1e2dc622\"")
        buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/\"")
        buildConfigField("String", "DATABASE_NAME", "\"city.db\"")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Viewmodel
    implementation(libs.lifecycleViewmodel)

    //LiveData
    implementation(libs.liveData)

    //Fragment
    implementation(libs.fragment)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //Coroutines
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    //Picasso
    implementation(libs.picasso)

    //DataStore
    implementation(libs.datastore)

    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.composeDebugs)

    //Glide
    implementation(libs.landscapist.glide)

    //Lottie
    implementation(libs.lottie.compose)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}