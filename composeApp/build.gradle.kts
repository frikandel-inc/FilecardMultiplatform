
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization") version "1.9.21"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    jvm("desktop")
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.5.0-alpha03")
            api(compose.foundation)
            api(compose.animation)
            api("moe.tlaster:precompose:1.5.10")
        }
        val desktopMain by getting
        desktopMain.dependencies {
            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.3")
            implementation(compose.desktop.currentOs)
            implementation("com.fazecast:jSerialComm:[2.0.0,3.0.0)")
        }
    }
}

android {
        repositories {
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
            google()
            gradlePluginPortal()
            mavenCentral()
            maven ( url = "https://jitpack.io" )
        }

    namespace = "com.filecard.multiplatform"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")


    defaultConfig {
        applicationId = "com.filecard.multiplatform"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "filecard"
            packageVersion = "1.0.0"
        }
    }
}
