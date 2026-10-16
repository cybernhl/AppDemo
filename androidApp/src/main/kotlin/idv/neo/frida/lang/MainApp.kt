package idv.neo.frida.lang

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
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
import idv.neo.frida.lang.livestream.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
            private set
    }

    private var activeActivities = 0

    @RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {
                activeActivities++
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {
                activeActivities--
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                appModule,
                sharedModule,
                utilsLoggerModule,
            )
        }
    }

    fun hasActiveActivities(): Boolean = activeActivities > 0
}
