package com.biqbila.bilapay.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.biqbila.bilapay.App
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(

        )
    }.koin.run {

    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Bilapay",
        ) {
            App()
        }
    }
}
