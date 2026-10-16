package idv.neo.frida.lang

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import idv.neo.frida.lang.di.appModule
import idv.neo.frida.lang.di.utilsLoggerModule
import idv.neo.frida.lang.im.App
import idv.neo.frida.lang.im.di.sharedModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            appModule,
            utilsLoggerModule,
            sharedModule
        )
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "LANG IM",
        ) {
            App()
        }
    }
}
