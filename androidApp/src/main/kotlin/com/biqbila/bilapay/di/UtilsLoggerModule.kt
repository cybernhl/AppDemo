package com.biqbila.bilapay.di

import android.util.Log
import io.ktor.client.plugins.logging.Logger
import org.koin.dsl.module

val utilsLoggerModule = module {
    val ktorLogger= object : Logger {
        override fun log(message: String) {
            Log.e( "", message)
        }
    }

    single { ktorLogger }
}



