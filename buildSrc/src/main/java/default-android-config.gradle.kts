@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
  id("com.android.base")
}

configure<BaseExtension> {
  compileSdkVersion(Versions.targetSdk)

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  defaultConfig {
    minSdkVersion(21)
    targetSdkVersion(Versions.targetSdk)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures.apply {
    buildConfig = false
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.compose
  }

  packagingOptions {
    excludes += "META-INF/AL2.0"
    excludes += "META-INF/LGPL2.1"
  }
}
