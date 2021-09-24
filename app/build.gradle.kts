plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)

}

android {
    compileSdkVersion(AndroidSdk.compile)
    ndkVersion  = AndroidSdk.NDKVERSION

    defaultConfig {
        ndkVersion  = AndroidSdk.NDKVERSION
        applicationId("com.weather.weather_app")
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
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
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(Libraries.kotlinStdLib)
//    implementation(project(Libraries.utilsModule))
//    implementation(project(Libraries.onBoardingModule))
//    implementation(project(Libraries.coreModule))
//    implementation(project(Libraries.loginModule))
//    implementation(project(Libraries.ordersModule))
    implementation (Libraries.koinCore)
    implementation (Libraries.koinViewModelLibrary)
    implementation(Libraries.ktxCore)
    implementation(Libraries.appCompat)
    implementation(Libraries.googleMaterial)
    implementation (Libraries.constraintLayout)
//    implementation(project(mapOf("path" to ":home")))
    implementation("com.google.firebase:firebase-messaging:22.0.0")
    testImplementation (TestLibraries.junit4)
    implementation (Libraries.corotineCore)


}