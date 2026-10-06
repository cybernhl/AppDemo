package com.biqbila.bilapay.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.biqbila.bilapay.App
import com.biqbila.bilapay.di.appModule
import com.biqbila.bilapay.di.networkEngineModule
import com.biqbila.bilapay.di.sharedModule
import com.biqbila.bilapay.di.utilsLoggerModule
import idv.neo.di.moudle.domainModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            appModule,
            domainModule,
            networkEngineModule,
            utilsLoggerModule,
            sharedModule
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
