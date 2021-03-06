plugins {
  id("com.android.library")
  id("default-android-config")
  id("release-config")
}

android {
  lintOptions {
    // Workaround for lint bug.
    disable += "InvalidFragmentVersionForActivityResult"
  }
}

dependencies {
  api(project(":compose-backstack"))

  implementation(Dependencies.Compose.foundation)
  implementation(Dependencies.Compose.tooling)

  testImplementation(Dependencies.Test.junit)
  testImplementation(Dependencies.Test.truth)

  androidTestImplementation(Dependencies.AndroidX.junitExt)
  androidTestImplementation(Dependencies.Compose.test)
}
