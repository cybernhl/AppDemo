package com.example.testkoinkib


import idv.neo.di.moudle.network.httpClientModule
import idv.neo.di.moudle.network.restful.resfulModule
import idv.neo.module.di.tag.APP_API_URL_TAG
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {

    includes(
        httpClientModule,
        resfulModule
    )

    /**
     * TriliveChat 官方客服 API 基底網址（SDK 全域單一真實來源）。
     */
    single(named(APP_API_URL_TAG)) {
        "https://trilivechat.chat/"
    }


}
