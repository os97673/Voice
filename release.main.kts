#!/usr/bin/env kotlin
@file:DependsOn("com.github.ajalt:clikt:2.5.0")

import com.github.ajalt.clikt.core.CliktCommand
import java.io.File
import java.util.Properties

class Release : CliktCommand() {

  override fun run() {
    execute("./gradlew", "app:bundleProprietaryRelease", "app:assembleProprietaryRelease")
    val appVersion = appVersion()
    val releaseFolder = File("releases", appVersion)
    releaseFolder.mkdirs()
    File("app/build/outputs/bundle/proprietaryRelease/app-proprietary-release.aab")
      .copyTo(File(releaseFolder, "app.aab"))
    File("app/build/outputs/apk/proprietary/release/app-proprietary-release.apk")
      .copyTo(File(releaseFolder, "app.apk"))
  }

  private fun appVersion(): String {
    val properties = File("gradle.properties").inputStream().use {
      Properties().apply {
        load(it)
      }
    }
    return properties["voiceVersionName"].toString()
  }

  private fun execute(vararg command: String) {
    ProcessBuilder(*command)
      .inheritIO()
      .start()
      .waitFor()
      .also { check(it == 0) }
  }
}

Release().main(args)
