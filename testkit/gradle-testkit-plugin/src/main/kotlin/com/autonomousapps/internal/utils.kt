// Copyright (c) 2024. Tony Robalik.
// SPDX-License-Identifier: Apache-2.0
package com.autonomousapps.internal

import org.gradle.api.artifacts.ProjectDependency
import org.gradle.util.GradleVersion
import java.util.Locale

internal fun String.capitalizeSafely(locale: Locale = Locale.ROOT): String {
  if (isNotEmpty()) {
    val firstChar = this[0]
    if (firstChar.isLowerCase()) {
      return buildString {
        val titleChar = firstChar.titlecaseChar()
        if (titleChar != firstChar.uppercaseChar()) {
          append(titleChar)
        } else {
          append(this@capitalizeSafely.substring(0, 1).uppercase(locale))
        }
        append(this@capitalizeSafely.substring(1))
      }
    }
  }
  return this
}

internal val isAtLeastGradle811: Boolean
  get() = GradleVersion.current().baseVersion >= GradleVersion.version("8.11")

internal val ProjectDependency.pathCompat: String
  get() =
    if (isAtLeastGradle811) {
      this.path
    } else {
      @Suppress("DEPRECATION") this.dependencyProject.path
    }
