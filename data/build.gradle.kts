plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {
    buildToolsVersion("30.0.1")
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = Application.applicationVersionCode
        versionName = Application.applicationVersionName
        testInstrumentationRunner = TestLibraries.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
}


dependencies {
    implementation(project(":domain"))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.koinCore)
    implementation(Libraries.koinAndroid)
    implementation(Libraries.lifeCycleExtensions)
    implementation(Libraries.gson)
    implementation(Libraries.okHttp)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGson)
    implementation(Libraries.okHttpInteceptor)

    androidTestImplementation(TestLibraries.testCore)
    androidTestImplementation(TestLibraries.extJunit)
    androidTestImplementation(TestLibraries.testRunner)
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.room)
    androidTestImplementation(TestLibraries.room)
}
