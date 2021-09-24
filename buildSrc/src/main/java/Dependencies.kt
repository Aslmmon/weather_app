
const val kotlinVersion = "1.5.10"


object BuildTypes {
    const val release = "release"
    const val production = "production"
    const val debug = "debug"
}

object BuildPlugins {


    object Versions {
        const val buildToolsVersion = "4.2.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val githubMaven = "com.github.dcendents:android-maven-plugin:1.2"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val GoogleMapsPlugin = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"

}

object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val NDKVERSION = "22.1.7171670"

    const val target = compile
    const val buildToolVersion = "30.0.3"
}

object BuildConfig {
    const val BASE_URL = "BASE_URL"

    object Release {
        const val BASE_URL = "\"https://homemade.arabaps.com/\""
    }

    object Production {
        const val BASE_URL = "\"https://api.openweathermap.org/\""
    }

    object Debug {
        const val BASE_URL = "\"https://homemade.arabaps.com\""
    }
}

object Libraries {
    private object Versions {
        const val okHttp= "4.9.0"
        const val jetpack = "1.3.0"
        const val constraintLayout = "2.0.4"
        const val ktx = "1.5.0"
        const val google_material = "1.3.0"
        const val corotine ="1.3.9"
        const val corotineCore = "1.1.0"
        const val dotsIndicator = "4.2"
        const val koinVersion = "2.0.1"
        const val ihsana = "2.0.7"
        const val retrofit = "2.9.0"
        const val corotineAdapter = "1.0.0"
        const val viewModelScop ="2.2.0"
        const val gson ="2.8.6"
        const val glide = "4.12.0"
        const val paging="3.0.0"
        const val circularImageView="3.1.0"
    }

    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat        = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val autoImagesSLider = "com.github.smarteist:autoimageslider:1.4.0-appcompat"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val hodenhofCircular = "de.hdodenhof:circleimageview:${Versions.circularImageView}"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.ktx}"
    const val googleMaterial          = "com.google.android.material:material:${Versions.google_material}"
    const val corotuines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.corotine}"
    const val corotineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.corotineCore}"
    const val DotsIndicatorLibrary = "com.tbuonomo:dotsindicator:${Versions.dotsIndicator}"
    const val koinViewModelLibrary = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    const val koinCore = "org.koin:koin-core:${Versions.koinVersion}"
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val RetrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val RetrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val corotineAdapter ="com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:${Versions.corotineAdapter}"
    const val corotineAdapterOlder = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val ihsanInterceptorLibrary = "com.github.ihsanbal:LoggingInterceptor:${Versions.ihsana}"
    const val viewModelScop = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelScop}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val paging3 = "androidx.paging:paging-runtime:${Versions.paging}"


    const val utilsModule = ":utils"
    const val coreModule = ":core"
    const val appModule = ":app"
    const val onBoardingModule = ":onboarding"
    const val loginModule=":login"
    const val homeModule = ":home"
    const val categoriesModule = ":categories"
    const val productsModule = ":products"
    const val cartModule = ":cart"
    const val servicesModule = ":services"
    const val mainModule = ":main"
    const val checkoutModule = ":checkout"
    const val ordersModule = ":orders"
    const val searchModule = ":search"
    const val filterModule = ":filter"


}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.2"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
    }
    const val junit4     = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso   = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}