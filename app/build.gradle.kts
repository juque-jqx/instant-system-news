plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        namespace = ProjectConfig.applicationId
        applicationId = ProjectConfig.applicationId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        jvmTarget = Kotlin.jvmVersion
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Compose
    val composeBom = platform(Compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.activityCompose)
    implementation(Compose.lifecycleViewModelCompose)
    implementation(Compose.navigationCompose)
    implementation(Compose.hiltNavigationCompose)

    implementation(Compose.uiToolingPreview)
    debugImplementation(Compose.uiTooling)

    androidTestImplementation(Compose.uiTestJunit)
    debugImplementation(Compose.uiTestManifest)

    // Coil
    implementation(Coil.coilCompose)

    //Core
    implementation(Core.ktx)

    //Lifecycle
    implementation(Lifecycle.runtime)

    // Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    // Retrofit
    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    // Room
//    implementation(Room.runtime)
//    implementation(Room.ktx)
//    kapt(Room.compiler)
//    androidTestImplementation(Room.testing)

    // Testing
    testImplementation(Test.junit4)
    androidTestImplementation(Test.junitAndroidExt)

    // Module
    implementation(project(Module.coreUi))
    implementation(project(Module.currentWeather))
    implementation(project(Module.forecasting))
    implementation(project(Module.news))
}