package chat.im.trilivechat.chatedin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import chat.im.trilivechat.chatedin.di.commonModule
import chat.im.trilivechat.chatedin.di.viewModelModule

import com.biqbila.bilapay.app.BuildConfig
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun main() {
    if (GlobalContext.getOrNull() == null) {
        startKoin {
            modules(commonModule, viewModelModule)
        }
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "TriliveChat DEMO",
        ) {
            App(
                siteId = BuildConfig.SITE_ID,
                agentId = BuildConfig.AGENT_ID
            )
        }
    }
}
