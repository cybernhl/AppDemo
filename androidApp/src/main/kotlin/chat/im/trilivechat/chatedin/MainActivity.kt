package chat.im.trilivechat.chatedin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import chat.im.trilivechat.chatedin.app.BuildConfig
import chat.im.trilivechat.chatedin.di.commonModule
import chat.im.trilivechat.chatedin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(this@MainActivity)
                modules(commonModule, viewModelModule)
            }
        }

        setContent {
            App(
                siteId = BuildConfig.SITE_ID,
                agentId = BuildConfig.AGENT_ID
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(siteId = BuildConfig.SITE_ID, agentId = BuildConfig.AGENT_ID)
}
