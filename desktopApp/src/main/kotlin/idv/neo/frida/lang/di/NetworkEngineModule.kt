package idv.neo.frida.lang.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.config
import org.koin.dsl.module
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

val networkEngineModule  = module {
    // Desktop 平台引擎配置
    single<HttpClientEngineFactory<*>> {
        CIO.config {
            https {
                trustManager = TrustAllCerts
            }
        }
    }
}


@Suppress("CustomX509TrustManager")
private object TrustAllCerts : X509TrustManager {
    @Suppress("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

    @Suppress("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}

