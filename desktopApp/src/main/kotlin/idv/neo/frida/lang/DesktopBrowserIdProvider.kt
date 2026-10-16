package idv.neo.frida.lang

import idv.neo.module.network.plugin.BrowserIdProvider
import idv.neo.module.utils.generateBrowserId

class DesktopBrowserIdProvider : BrowserIdProvider {
    override val browserId: String = generateBrowserId()
}