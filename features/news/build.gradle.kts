plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.news"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
}

dependencies {

    // Compose
    val composeBom = platform(Compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.material3)
    implementation(Compose.activityCompose)
    implementation(Compose.lifecycleViewModelCompose)
    implementation(Compose.navigationCompose)
    implementation(Compose.hiltNavigationCompose)

    implementation(Compose.uiToolingPreview)
    debugImplementation(Compose.uiTooling)

    androidTestImplementation(Compose.uiTestJunit)
    debugImplementation(Compose.uiTestManifest)

    // Room
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)
    androidTestImplementation(Room.testing)

    // Module
    implementation(project(Module.coreUi))
    implementation(project(Module.data))

    //coil
    implementation(Coil.coilCompose)

    // Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    //Test
    testImplementation(Test.junit4)
    testImplementation(Test.mockk)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockito)
    testImplementation(Test.androidxTesting)
    testImplementation(Test.turbine)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Test.junitAndroidExt)

    //paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")

    // Module
    implementation(project(Module.core))
    implementation(project(Module.coreUi))
}