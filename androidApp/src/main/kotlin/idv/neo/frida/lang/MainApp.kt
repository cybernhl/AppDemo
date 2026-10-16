package idv.neo.frida.lang

import android.app.Activity
import android.app.Application
import android.os.Bundle
import idv.neo.frida.lang.di.appModule
import idv.neo.frida.lang.di.utilsLoggerModule
import idv.neo.frida.lang.im.di.sharedModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
            private set
    }

    private var activeActivities = 0

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
                utilsLoggerModule,
                sharedModule
            )
        }.koin.run {
            CoroutineScope(Dispatchers.Default).launch {

            }
        }
    }

    fun hasActiveActivities(): Boolean = activeActivities > 0
}