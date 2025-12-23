import com.android.build.gradle.api.ApkVariantOutput
import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
    //id("com.google.gms.google-services")
    //id("com.google.firebase.crashlytics")
}
val appInfo: Map<String, String> = mapOf(
    "appName" to "Pos Manager",
    "bundleId" to "com.izoe.pos_manager",
    "minSdkVersion" to "24",
    "targetSdkVersion" to "35",
    "ndkVersion" to "27.0.12077973",
)
android {
    namespace = "com.izoe.pos_manager"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = appInfo["ndkVersion"] as String

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }


    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")

        }
        maybeCreate("dev").apply {
            assets.srcDir("src/dev/")

        }
        maybeCreate("prod").apply {
            assets.srcDir("src/prod/")

        }
        maybeCreate("prelive").apply {
            assets.srcDir("src/prelive/")

        }
    }

    fun loadSigningConfigProps(path: String): Properties {
        val props = Properties()
        props.load(FileInputStream(rootProject.file(path)))
        return props
    }

    signingConfigs {
        getByName("debug") {
            val p = loadSigningConfigProps("key_properties/debug/debug.properties")
            keyAlias = p["keyAlias"] as String
            keyPassword = p["keyPassword"] as String
            storeFile = file(p["storeFile"] as String)
            storePassword = p["storePassword"] as String
        }
        create("devRelease") {
            val p = loadSigningConfigProps("key_properties/release/dev.properties")
            keyAlias = p["keyAlias"] as String
            keyPassword = p["keyPassword"] as String
            storeFile = file(p["storeFile"] as String)
            storePassword = p["storePassword"] as String
        }
        create("preliveRelease") {
            val p = loadSigningConfigProps("key_properties/release/prelive.properties")
            keyAlias = p["keyAlias"] as String
            keyPassword = p["keyPassword"] as String
            storeFile = file(p["storeFile"] as String)
            storePassword = p["storePassword"] as String
        }
        create("prodRelease") {
            val p = loadSigningConfigProps("key_properties/release/prod.properties")
            keyAlias = p["keyAlias"] as String
            keyPassword = p["keyPassword"] as String
            storeFile = file(p["storeFile"] as String)
            storePassword = p["storePassword"] as String
        }
    }
    flavorDimensions += "flavor"
    productFlavors {
        create("dev") {
            dimension = "flavor"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            signingConfig = signingConfigs.getByName("devRelease")
            versionCode = 1
            versionName = "1.0.0"
            resValue("string", "app_name", "${appInfo["appName"]} Dev")
        }
        create("prelive") {
            dimension = "flavor"
            applicationIdSuffix = ".prelive"
            versionNameSuffix = "-prelive"
            signingConfig = signingConfigs.getByName("preliveRelease")
            versionCode = 1
            versionName = "1.0.0"
            resValue("string", "app_name", "${appInfo["appName"]} Prelive")
        }
        create("prod") {
            dimension = "flavor"
            signingConfig = signingConfigs.getByName("prodRelease")
            applicationIdSuffix = ".win"
            versionCode = 1;
            versionName = "1.0.0"
            resValue("string", "app_name", appInfo["appName"] as String)

        }
    }

    defaultConfig {
        applicationId = appInfo["bundleId"] as String
        minSdk = appInfo["minSdkVersion"].toString().toInt()
        targetSdk = appInfo["targetSdkVersion"].toString().toInt()

    }


    buildTypes {
        getByName("debug") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }

}

flutter {
    source = "../.."
}
