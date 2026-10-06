package com.biqbila.bilapay.di

import com.biqbila.bilapay.app.BuildConfig
import idv.neo.module.di.tag.APP_API_URLS_TAG
import idv.neo.module.di.tag.APP_API_URL_TAG
import idv.neo.module.di.tag.APP_DATASTORE_FILE_TAG
import idv.neo.module.di.tag.APP_RUNTIME_ENV_TAG
import idv.neo.module.di.tag.APP_RUNTIME_TYPE_TAG
import idv.neo.module.di.tag.APP_VERSION_TAG
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    // 提供單一 URL 給 NetworkModule 使用
    single(named(APP_API_URL_TAG)) { BuildConfig.API_URL }
    // 提供初始 URL 列表 (待後續多域名切換使用)
    single(named(APP_API_URLS_TAG)) { BuildConfig.API_URLS.toList() }
//
    single(named(APP_RUNTIME_ENV_TAG)) { BuildConfig.FLAVOR }

    single(named(APP_RUNTIME_TYPE_TAG)) { BuildConfig.BUILD_TYPE }

    single(named(APP_VERSION_TAG)) { BuildConfig.VERSION_NAME }

    // 提供 DataStore 檔案名稱
    single(named( APP_DATASTORE_FILE_TAG)) { "bilapay.preferences_pb" }

}
