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
        maven (url = "http://releases.payworks.io/artifactory/mpos")
        maven (url = "http://releases.payworks.io/artifactory/mpos-staging")
        maven (url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven (url = "https://oss.sonatype.org/content/repositories/releases")
        maven (url = "https://maven.fabric.io/public")
    }
}

tasks.register("clean").configure {
    delete("build")
}
