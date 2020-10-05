plugins {
    id(BuildPlugins.kotlinLibrary)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.koinCore)
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.kotlinCoroutinesAndroid)
}

java {
    sourceCompatibility  = JavaVersion.VERSION_1_8
    targetCompatibility  = JavaVersion.VERSION_1_8
}
