const val kotlinVersion = "1.3.70"

object Application {
    const val applicationId = "br.com.rrodovalho.marvelapp"
    const val applicationVersionCode = 1
    const val applicationVersionName = "1.0"
}

object BuildPlugins {

    private object Versions {
        const val buildTools = "3.4.1"
        const val googleServicesVersion = "4.3.3"
        const val firebaseCrashlyticsVersion = "2.2.0"
        const val navigation = "2.1.0"
    }

    const val androidGradlePlugin       = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val googleServicesGradlePlugin = "com.google.gms:google-services:${Versions.googleServicesVersion}"
    const val kotlinGradlePlugin        = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val androidApplication        = "com.android.application"
    const val androidLibrary            = "com.android.library"
    const val kotlinLibrary             = "kotlin"
    const val kotlinAndroid             = "kotlin-android"
    const val kotlinAndroidExtensions   = "kotlin-android-extensions"
    const val kotlinKapt                = "kotlin-kapt"
    const val googleServices            = "com.google.gms.google-services"
    const val navigationSafeArgs        = "androidx.navigation.safeargs.kotlin"
}

object AndroidSdk {
    const val min = 22
    const val compile = 29
    const val target = compile
}

object Libraries {
    private object Versions {
        const val jetPack = "1.0.2"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.0.1"
        const val googleMaterial = "1.0.0"
        const val legacySupport = "1.0.0"
        const val navigation = "2.1.0"
        const val lifecycle = "2.0.0"
        const val koin = "2.0.1"
        const val kotlinCoroutines = "1.3.7"
        const val firebaseAnalytics = "17.4.4"
        const val crashlytics       = "17.1.1"
        const val room              = "2.2.3"
        const val debugDatabase     = "1.0.6"
        const val okHttp            = "2.7.4"
        const val gson = "2.8.6"
        const val retrofit = "2.6.0"
        const val okHttpInterceptor = "3.8.0"
        const val glideVersion = "3.8.0"
    }

    const val kotlinStdLib             = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat                = "androidx.appcompat:appcompat:${Versions.jetPack}"
    const val legacySupport            = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val constraintLayout         = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore                  = "androidx.core:core-ktx:${Versions.ktx}"
    const val googleMaterial           = "com.google.android.material:material:${Versions.googleMaterial}"
    const val navigationFragment       = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi             = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val lifeCycleViewModel       = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifeCycleExtensions      = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifeCycleCompiler        = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val koinCore                 = "org.koin:koin-core:${Versions.koin}"
    const val koinAndroid              = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidScopes        = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinAndroidViewModel     = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val kotlinCoroutinesCore     = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesAndroid  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val roomRuntime              = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx                  = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler             = "androidx.room:room-compiler:${Versions.room}"
    const val gson                     = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttp                   = "com.squareup.okhttp:okhttp:${Versions.okHttp}"
    const val okHttpInteceptor         = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpInterceptor}"
    const val retrofit                 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson             = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val glide                    = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val androidXTest = "1.2.0"
        const val espresso = "3.2.0"
        const val androidArchTesting = "2.0.0"
        const val koin = "2.0.1"
        const val kotlinCorotines = "1.3.2"
        const val room = "2.2.3"
    }
    const val junit4                    = "junit:junit:${Versions.junit4}"
    const val testCore                  = "androidx.test:core:${Versions.androidXTest}"
    const val extJunit                  = "androidx.test.ext:junit:1.0.0"
    const val testRunner                = "androidx.test:runner:${Versions.androidXTest}"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val espresso                  = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val archCoreTesting           = "androidx.arch.core:core-testing:${Versions.androidArchTesting}"
    const val koin                      = "org.koin:koin:${Versions.koin}"
    const val kotlinCoroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCorotines}"
    const val room                      = "androidx.room:room-testing:${Versions.room}"
}
