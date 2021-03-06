plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kapt)


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
    implementation(project(Libraries.utilsModule))
    implementation(project(Libraries.coreModule))
    implementation (Libraries.koinCore)
    implementation (Libraries.koinViewModelLibrary)
    implementation(Libraries.ktxCore)
    implementation (Libraries.corotuines)
    implementation (Libraries.Retrofit)
    implementation (Libraries.RetrofitRx)
    implementation (Libraries.RetrofitGson)
    implementation (Libraries.corotineAdapterOlder)
    implementation (Libraries.ihsanInterceptorLibrary)
    implementation (Libraries.okHttp)
    implementation (Libraries.okHttpLogging)
    implementation(Libraries.appCompat)
    implementation(Libraries.glide)
    implementation(Libraries.googleMaterial)
    implementation (Libraries.constraintLayout)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation (Libraries.corotineCore)
    implementation ("com.google.android.gms:play-services-location:18.0.0")


}