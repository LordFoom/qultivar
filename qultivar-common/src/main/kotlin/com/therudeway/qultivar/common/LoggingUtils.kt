// Logging.kt
package com.therudeway.qultivar.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggingUtils {
    inline fun <reified T> logger(): Logger = LoggerFactory.getLogger(T::class.java)
}