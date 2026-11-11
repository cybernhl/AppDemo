package chat.im.trilivechat.chatedin

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import chat.im.trilivechat.chatedin.di.commonModule
import chat.im.trilivechat.chatedin.di.viewModelModule
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    try {
        startKoin {
            modules(commonModule, viewModelModule)
        }
    } catch (_: Exception) {
        // Already started
    }

    ComposeViewport {
        App(
            siteId = BuildConfig.SITE_ID,
            agentId = BuildConfig.AGENT_ID
        )
    }
}
