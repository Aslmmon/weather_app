// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {

    val kotlin_version by extra("1.5.10")
    repositories {
        google()
        mavenCentral()

    }
    dependencies {


        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
//        classpath ("com.google.gms:google-services:4.3.10")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

    }

}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven ( "https://jitpack.io" )
        maven (  "https://maven.google.com" )
        jcenter() // Warning: this repository is going to shut down soon
    }
}


tasks.register("clean").configure {
    delete("build")
}