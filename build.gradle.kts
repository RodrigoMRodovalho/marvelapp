buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath (BuildPlugins.googleServicesGradlePlugin)
        classpath (BuildPlugins.navigationSafeArgsGradlePlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven (url = "https://jitpack.io")
    }
}

tasks.register("clean").configure {
    delete("build")
}
