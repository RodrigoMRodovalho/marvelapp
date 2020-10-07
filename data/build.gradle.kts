import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }
    buildTypes {

        getByName("debug"){
            addBuildConfigParameters(this)
        }

        getByName("release") {
            addBuildConfigParameters(this)
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("androidTest").assets.srcDirs("schemas")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
}

fun addBuildConfigParameters(buildType: com.android.build.gradle.internal.dsl.BuildType) {
    val apiKey: String = gradleLocalProperties(rootDir).getProperty("apiKey")
    val ts: String = gradleLocalProperties(rootDir).getProperty("ts")
    val hash: String = gradleLocalProperties(rootDir).getProperty("hash")

    with (buildType) {
        buildConfigField("String", "apiKeyParam", "\"apikey\"")
        buildConfigField("String", "apiKeyValue", "\"$apiKey\"")
        buildConfigField("String", "tsParam", "\"ts\"")
        buildConfigField("String", "tsValue", "\"$ts\"")
        buildConfigField("String", "hashParam", "\"hash\"")
        buildConfigField("String", "hashValue", "\"$hash\"")
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
    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)

    kapt (Libraries.roomCompiler)

    debugImplementation (Libraries.debugDatabase)

    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.room)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.hamcrest)
    testImplementation(TestLibraries.kotlinCoroutines)


    androidTestImplementation(TestLibraries.testCore)
    androidTestImplementation(TestLibraries.extJunit)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.room)
}
