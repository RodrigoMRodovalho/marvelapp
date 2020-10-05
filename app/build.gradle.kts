plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.navigationSafeArgs)
}

android {
    buildToolsVersion("30.0.1")
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = Application.applicationId
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
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}


dependencies {

//    implementation(project(":data"))
//    implementation(project(":domain"))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.legacySupport)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.googleMaterial)
    implementation(Libraries.koinCore)
    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinAndroidScopes)
    implementation(Libraries.koinAndroidViewModel)
    implementation(Libraries.lifeCycleViewModel)
    implementation(Libraries.lifeCycleExtensions)
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.kotlinCoroutinesAndroid)
    implementation(Libraries.glide)

    kapt(Libraries.lifeCycleCompiler)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
    androidTestImplementation(TestLibraries.archCoreTesting)
}
