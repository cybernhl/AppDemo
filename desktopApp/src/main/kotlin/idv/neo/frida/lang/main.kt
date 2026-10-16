package idv.neo.frida.lang

import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import idv.neo.di.api.livestream.lang.apiModule
import idv.neo.di.moudle.domainModule
import idv.neo.di.moudle.network.httpClientModule
import idv.neo.di.moudle.network.plugin.defaultHttpClientPluginModule
import idv.neo.di.moudle.network.plugin.httpClientPluginModule
import idv.neo.di.moudle.network.restful.resfulModule
import idv.neo.di.moudle.utils.utilsModule
import idv.neo.di.repository.lang.repositoryModule
import idv.neo.frida.lang.di.appModule
import idv.neo.frida.lang.di.langServicePatternModule
import idv.neo.frida.lang.di.networkEngineModule
import idv.neo.frida.lang.di.snifferModule
import idv.neo.frida.lang.di.utilsLoggerModule
import idv.neo.frida.lang.livestream.App
import idv.neo.frida.lang.livestream.di.sharedModule
import org.jetbrains.skia.Image
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            appModule,
            sharedModule,
            utilsLoggerModule,
        )
    }

    application {
        val windowIcon = remember {
            try {
                Thread.currentThread().contextClassLoader.getResourceAsStream("icon.png")?.use { inputStream ->
                    BitmapPainter(Image.makeFromEncoded(inputStream.readBytes()).toComposeImageBitmap())
                }
            } catch (_: Exception) {
                null
            }
        }

        Window(
            onCloseRequest = ::exitApplication,
            title = "浪Live_Desktop",
            icon = windowIcon
        ) {
            App()
        }
    }
}
