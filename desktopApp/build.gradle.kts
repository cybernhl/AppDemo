import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildConfig)
}

group = "com.biqbila.bilapay.app"
version = "1.0-SNAPSHOT"

buildConfig {
    useKotlinOutput()
    packageName("com.biqbila.bilapay.app")
    buildConfigField("String", "SITE_ID", "\"\"")
    buildConfigField("String", "AGENT_ID", "\"\"")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
//            implementation(project(":sdk_core"))
    implementation("com.github.cybernhl.SDK:sdk_core:trilivechat.0.0.1")
//            api(project(":viewmodel"))
    implementation("com.github.cybernhl.SDK:viewmodel:trilivechat.0.0.1")
//    implementation(project(":shared"))
    implementation("com.github.cybernhl.SDK:shared:trilivechat.0.0.1")
    implementation(libs.koin.core)
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutines.swing)

    implementation(libs.jetbrains.compose.ui.tooling.preview)
}

compose.desktop {
    application {
        mainClass = "chat.im.trilivechat.chatedin.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "chat.im.trilivechat.chatedin"
            packageVersion = "1.0.0"
        }
    }
}