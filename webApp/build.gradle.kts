import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildConfig)
}

buildConfig {
    useKotlinOutput()
    packageName("chat.im.trilivechat.chatedin")
    buildConfigField("String", "SITE_ID", "\"\"")
    buildConfigField("String", "AGENT_ID", "\"\"")
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "webApp.js" // ◄── 強制 Webpack 產出 webApp.js
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "webApp.js" // ◄── 強制 Webpack 產出 webApp.js
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
//            implementation(project(":sdk_core"))
            implementation("com.github.cybernhl.SDK:sdk_core:trilivechat.0.0.1")
//            api(project(":viewmodel"))
            implementation("com.github.cybernhl.SDK:viewmodel:trilivechat.0.0.1")
//    implementation(project(":shared"))
            implementation("com.github.cybernhl.SDK:shared:trilivechat.0.0.1")
            implementation(libs.jetbrains.compose.ui)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
        }
    }
}
