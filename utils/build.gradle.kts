plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
}
android {
    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion(AndroidSdk.buildToolVersion)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        testInstrumentationRunner ("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles ("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField(
                "String",

                BuildConfig.BASE_URL,
                BuildConfig.Release.BASE_URL
            )

        }

        getByName(BuildTypes.debug) {
            isDebuggable = true
          //  isShrinkResources = true
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField(
                "String",

                BuildConfig.BASE_URL,
                BuildConfig.Production.BASE_URL
            )


        }

//        getByName(BuildTypes.production) {
//            isDebuggable = true
//            //  isShrinkResources = true
//            isMinifyEnabled = true
//
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//
//            buildConfigField(
//                "String",
//
//                BuildConfig.BASE_URL,
//                BuildConfig.Production.BASE_URL
//            )
//
//
//        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
//    implementation(project(Libraries.coreModule))
    implementation (Libraries.corotuines)
    implementation (Libraries.koinCore)
    api (Libraries.koinViewModelLibrary)
    implementation (Libraries.Retrofit)
    implementation (Libraries.RetrofitRx)
    implementation (Libraries.RetrofitGson)
    implementation (Libraries.corotineAdapterOlder)
    implementation (Libraries.ihsanInterceptorLibrary)
    implementation (Libraries.okHttp)
    implementation (Libraries.okHttpLogging)
    implementation (Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)
    implementation(Libraries.appCompat)
    implementation(Libraries.googleMaterial)
    implementation (Libraries.constraintLayout)
    implementation("androidx.navigation:navigation-runtime:2.3.5")
    implementation("androidx.navigation:navigation-fragment:2.3.5")
    implementation("com.google.firebase:firebase-messaging:22.0.0")
    testImplementation (TestLibraries.junit4)
    implementation(Libraries.glide)
    implementation ("com.github.ybq:Android-SpinKit:1.4.0")
    implementation(    "com.chaos.view:pinview:1.4.4")
    implementation(Libraries.hodenhofCircular)
    implementation(Libraries.autoImagesSLider)
    implementation("io.github.nikartm:image-support:2.0.0")






}