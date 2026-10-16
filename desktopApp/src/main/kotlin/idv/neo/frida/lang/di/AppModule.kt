package idv.neo.frida.lang.di

import idv.neo.frida.lang.DesktopBrowserIdProvider
import idv.neo.module.di.tag.APP_API_URL_TAG
import idv.neo.module.network.plugin.BrowserIdProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(APP_API_URL_TAG)) { "https://api.lang.live/" }
    single<BrowserIdProvider> { DesktopBrowserIdProvider() }
}
