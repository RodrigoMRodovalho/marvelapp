plugins {
    id(BuildPlugins.kotlinLibrary)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.koinCore)
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.kotlinCoroutinesAndroid)
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.hamcrest)
    testImplementation(TestLibraries.kotlinCoroutines)
}

java {
    sourceCompatibility  = JavaVersion.VERSION_1_8
    targetCompatibility  = JavaVersion.VERSION_1_8
}
